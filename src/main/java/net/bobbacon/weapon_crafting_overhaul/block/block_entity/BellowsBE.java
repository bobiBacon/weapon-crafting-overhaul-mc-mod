package net.bobbacon.weapon_crafting_overhaul.block.block_entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Map;

public class BellowsBE extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache= GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation BLOW_ANIM = RawAnimation.begin().thenPlay("animation.model.blow").thenWait(4);
    private static final String BLOW_ANIM_NAME = "blow";
    private final String BLOWING_CONTROLLER = "blowing_controller";
    public BellowsBE(BlockPos pos, BlockState state) {
        super(ModBEs.BELLOWS_BLOCK_ENTITY_TYPE, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
//        controllerRegistrar.add(new AnimationController<>(this,"controller",0,this::predictate));
        controllerRegistrar.add(new AnimationController<>(this, BLOWING_CONTROLLER,0, state -> PlayState.STOP)
                .triggerableAnim(BLOW_ANIM_NAME,BLOW_ANIM));
    }

    //    private PlayState predictate(AnimationState<BellowsBE> bellowsBEAnimationState) {
//
//    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public boolean onUse(){
        for (AnimationController<?> controller: getAnimatableInstanceCache().getManagerForId(0).getAnimationControllers().values()){
            if (controller.getAnimationState()== AnimationController.State.RUNNING){
                return false;
            }
        }

        triggerAnim(BLOWING_CONTROLLER,BLOW_ANIM_NAME);
        return true;
    }

}
