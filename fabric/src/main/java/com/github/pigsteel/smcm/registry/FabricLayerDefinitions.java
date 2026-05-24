package com.github.pigsteel.smcm.registry;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

public class FabricLayerDefinitions {
    private FabricLayerDefinitions() {

    }

    public static void registerModelLayers() {
        LayerDefinitions.registerModelLayers((location, definition) ->
                ModelLayerRegistry.registerModelLayer(location, definition::get)
        );
    }
}
