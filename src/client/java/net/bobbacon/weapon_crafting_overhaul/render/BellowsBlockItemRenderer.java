package net.bobbacon.weapon_crafting_overhaul.render;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.item.BellowsBlockItem;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BellowsBlockItemRenderer extends GeoItemRenderer<BellowsBlockItem> implements RenderProvider {
    private final BellowsBlockItemRenderer renderer= new BellowsBlockItemRenderer();


    public BellowsBlockItemRenderer() {
        super(new DefaultedItemGeoModel<>(new Identifier(WeaponCraftingOverhaul.MOD_ID,"bellows")));
    }

    @Override
    public BuiltinModelItemRenderer getCustomRenderer() {
        return this.renderer;
    }
}
