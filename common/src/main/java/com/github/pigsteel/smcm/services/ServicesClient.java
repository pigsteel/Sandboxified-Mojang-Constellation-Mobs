package com.github.pigsteel.smcm.services;

import com.github.pigsteel.smcm.services.client.IClientRegistryHelper;

public final class ServicesClient {
    public static final IClientRegistryHelper CLIENT_REGISTRY = Services.load(IClientRegistryHelper.class);

    private ServicesClient() {
    }
}
