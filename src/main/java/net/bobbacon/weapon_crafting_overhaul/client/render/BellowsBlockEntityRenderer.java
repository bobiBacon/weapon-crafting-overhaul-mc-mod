package net.bobbacon.weapon_crafting_overhaul.client.render;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.block.block_entity.BellowsBE;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BellowsBlockEntityRenderer extends GeoBlockRenderer<BellowsBE> {
    public BellowsBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new DefaultedBlockGeoModel<>(new Identifier(WeaponCraftingOverhaul.MOD_ID,"bellows")));
//        super(new BellowsGeoModel());
    }
}
