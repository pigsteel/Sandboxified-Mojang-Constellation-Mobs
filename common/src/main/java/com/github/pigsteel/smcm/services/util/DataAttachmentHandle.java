package com.github.pigsteel.smcm.services.util;

import java.util.function.Supplier;

public final class DataAttachmentHandle<T> {
    private final String name;
    private final Supplier<T> defaultValueSupplier;
    private final boolean persistent;

    private Supplier<?> platformTypeSupplier;

    public DataAttachmentHandle(
            String name,
            Supplier<T> defaultValueSupplier,
            boolean persistent
    ) {
        this.name = name;
        this.defaultValueSupplier = defaultValueSupplier;
        this.persistent = persistent;
    }

    public String name() {
        return name;
    }

    public Supplier<T> defaultValueSupplier() {
        return defaultValueSupplier;
    }

    public Object platformType() {
        if (platformTypeSupplier == null) {
            throw new IllegalStateException("Attachment " + name + " has not been registered yet.");
        }

        return platformTypeSupplier.get();
    }

    public void setPlatformTypeSupplier(Supplier<?> platformTypeSupplier) {
        if (this.platformTypeSupplier != null) {
            throw new IllegalStateException("Attachment " + name + " was already registered.");
        }

        this.platformTypeSupplier = platformTypeSupplier;
    }
}
