//package net.bobbacon.weapon_crafting_overhaul.render.geo_model;
//
//import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
//import net.bobbacon.weapon_crafting_overhaul.block.block_entity.BellowsBE;
//import net.minecraft.util.Identifier;
//import software.bernie.geckolib.core.animatable.GeoAnimatable;
//import software.bernie.geckolib.model.GeoModel;
//
//public class BellowsGeoModel extends GeoModel<BellowsBE> {
//    private final Identifier model = new Identifier(WeaponCraftingOverhaul.MOD_ID, "geo/block/bellows.geo.json");
//    private final Identifier texture = new Identifier(WeaponCraftingOverhaul.MOD_ID, "textures/block/bellows.png");
//    private final Identifier animations = new Identifier(WeaponCraftingOverhaul.MOD_ID, "animations/block/bellows.animation.json");
//    @Override
//    public Identifier getModelResource(BellowsBE animatable) {
//        return model;
//    }
//
//    @Override
//    public Identifier getTextureResource(BellowsBE animatable) {
//        return texture;
//    }
//
//    @Override
//    public Identifier getAnimationResource(BellowsBE animatable) {
//        return animations;
//    }
//}
