package net.bobbacon.races.mixin.client;

import net.bobbacon.races.gui.TalentTreeScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.spell_engine.api.spell.Spell;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.realms.task.LongRunningTask.setScreen;

@Mixin(MinecraftClient.class)
public abstract class ClientMixin {
    @Shadow
    public ClientPlayerEntity player;

    @Shadow public abstract void setScreen(@Nullable Screen screen);

    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void inject(CallbackInfo ci){
//        this.setScreen(new TalentTreeScreen());
    }

    @Unique
    public final KeyBinding TalentTreeKey = new KeyBinding("key.talent_tree", GLFW.GLFW_KEY_N, KeyBinding.MISC_CATEGORY);

}
