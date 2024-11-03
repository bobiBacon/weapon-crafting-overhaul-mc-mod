package net.bobbacon.weapon_crafting_overhaul.block.block_entity;

import net.bobbacon.weapon_crafting_overhaul.block.Bellows;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
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
//    @Nullable
//    protected Block connectedFurnace;
    private final AnimatableInstanceCache cache= GeckoLibUtil.createInstanceCache(this);
    protected static final RawAnimation BLOW_ANIM = RawAnimation.begin().thenPlay("animation.model.blow").thenWait(4);
    private static final String BLOW_ANIM_NAME = "blow";
    private static final String BLOWING_CONTROLLER = "blowing_controller";
    public BlockPos frontBlockPos;
    private final String FRONT_BLOCK_X_KEY= "front_block_x";
    private final String FRONT_BLOCK_Y_KEY= "front_block_y";
    private final String FRONT_BLOCK_Z_KEY= "front_block_z";

    public BellowsBE(BlockPos pos, BlockState state) {
        super(ModBEs.BELLOWS_BLOCK_ENTITY_TYPE, pos, state);
        switch (state.get(Bellows.FACING)){
            case EAST -> frontBlockPos=new BlockPos(pos.getX()+1,pos.getY(),pos.getZ());
            case WEST -> frontBlockPos=new BlockPos(pos.getX()-1,pos.getY(),pos.getZ());
            case SOUTH -> frontBlockPos=new BlockPos(pos.getX(),pos.getY(),pos.getZ()+1);
            case NORTH -> frontBlockPos=new BlockPos(pos.getX(),pos.getY(),pos.getZ()-1);
            default -> throw new RuntimeException("Bellows does not have a direction");
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt(FRONT_BLOCK_X_KEY,frontBlockPos.getX());
        nbt.putInt(FRONT_BLOCK_Y_KEY,frontBlockPos.getY());
        nbt.putInt(FRONT_BLOCK_Z_KEY,frontBlockPos.getZ());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        frontBlockPos= new BlockPos(
                nbt.getInt(FRONT_BLOCK_X_KEY),
                nbt.getInt(FRONT_BLOCK_Y_KEY),
                nbt.getInt(FRONT_BLOCK_Z_KEY)
        );
    }
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
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
