package net.bobbacon.weapon_crafting_overhaul.render;

import net.bobbacon.weapon_crafting_overhaul.block.block_entity.ModBEs;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class ModEntitiesRenderers {


    public static void init(){
        BlockEntityRendererFactories.register(ModBEs.BELLOWS_BLOCK_ENTITY_TYPE,BellowsBlockEntityRenderer::new);
    }
}
