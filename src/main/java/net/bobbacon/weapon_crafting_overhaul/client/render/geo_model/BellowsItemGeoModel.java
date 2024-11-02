package net.bobbacon.weapon_crafting_overhaul.client.render.geo_model;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.block.block_entity.BellowsBE;
import net.bobbacon.weapon_crafting_overhaul.item.BellowsBlockItem;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BellowsItemGeoModel extends GeoModel<BellowsBlockItem> {
    private final Identifier model = new Identifier(WeaponCraftingOverhaul.MOD_ID, "geo/block/bellows.geo.json");
    private final Identifier texture = new Identifier(WeaponCraftingOverhaul.MOD_ID, "textures/block/bellows.png");
    private final Identifier animations = new Identifier(WeaponCraftingOverhaul.MOD_ID, "animations/block/bellows.animation.json");
    @Override
    public Identifier getModelResource(BellowsBlockItem animatable) {
        return model;
    }

    @Override
    public Identifier getTextureResource(BellowsBlockItem animatable) {
        return texture;
    }

    @Override
    public Identifier getAnimationResource(BellowsBlockItem animatable) {
        return animations;
    }

}
