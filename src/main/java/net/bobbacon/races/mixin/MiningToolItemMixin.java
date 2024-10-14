package net.bobbacon.races.mixin;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.datagen.ModBlockTagsGenerator;
import net.bobbacon.races.forging.ModMiningLevels;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MiningToolItem.class)
public abstract class MiningToolItemMixin extends ToolItemMixin {
    @Shadow @Final private TagKey<Block> effectiveBlocks;


//    /**
//     * @author
//     * @reason
//     */
//    @Overwrite
//    public boolean isSuitableFor(BlockState state){
//        RacesModForMyServer.LOGGER.warn("isSuitableFor");
//
//        int i = this.getMaterial().getMiningLevel();
//        RacesModForMyServer.LOGGER.info(String.valueOf(i));
//
//        if (i < 4 && state.isIn(BlockTags.NEEDS_DIAMOND_TOOL)) {
//            return false;
//        }
//        if (i < 3 && state.isIn(BlockTags.NEEDS_IRON_TOOL)) {
//            return false;
//        }
//        if (i < 2 && state.isIn(BlockTags.NEEDS_STONE_TOOL)) {
//            return false;
//        }
//
//        if (i < ModMiningLevels.COPPER && state.isIn(MyTagsGenerator.NEEDS_COPPER_TOOL)) {
//            return false;
//        }
//        return state.isIn(effectiveBlocks);
//
//    }

    @Inject(method = "isSuitableFor",at = @At("HEAD"), cancellable = true)
    private void injectIsSuitableFor(BlockState state, CallbackInfoReturnable<Boolean> cir){
        RacesModForMyServer.LOGGER.warn("isSuitableFor");

        int i = this.getMaterial().getMiningLevel();
        RacesModForMyServer.LOGGER.info(String.valueOf(i));
        RacesModForMyServer.LOGGER.info("diamond");
        RacesModForMyServer.LOGGER.info(state.streamTags().toString());
        if (i < 4 && state.isIn(BlockTags.NEEDS_DIAMOND_TOOL)) {
            RacesModForMyServer.LOGGER.info("not diamond");
            cir.setReturnValue(false);
            return;
        }
        RacesModForMyServer.LOGGER.info("iron");

        if (i < 3 && state.isIn(BlockTags.NEEDS_IRON_TOOL)) {
            RacesModForMyServer.LOGGER.info("not iron");
            cir.setReturnValue(false);
            return;
        }
        RacesModForMyServer.LOGGER.info("stone");

        if (i < 2 && state.isIn(BlockTags.NEEDS_STONE_TOOL)) {
            RacesModForMyServer.LOGGER.info("not stone");
            cir.setReturnValue(false);
            return;
        }
        RacesModForMyServer.LOGGER.info("copper");

        if (i < ModMiningLevels.COPPER && state.isIn(ModBlockTagsGenerator.NEEDS_COPPER_TOOL)) {
            RacesModForMyServer.LOGGER.info("not copper");
            cir.setReturnValue(false);
            return;
        }
        RacesModForMyServer.LOGGER.info(String.valueOf(i));
        cir.setReturnValue(state.isIn(effectiveBlocks));

    }


}
