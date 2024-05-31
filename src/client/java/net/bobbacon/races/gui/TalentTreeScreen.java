package net.bobbacon.races.gui;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.key_blinds.ModKeybindings;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import static net.bobbacon.races.RacesModForMyServer.MOD_ID;

public class TalentTreeScreen extends BaseOwoScreen<FlowLayout> {

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        // TODO
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        // TODO
        rootComponent
                .surface(Surface.VANILLA_TRANSLUCENT)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);

        rootComponent.child(
                Containers.verticalFlow(Sizing.fill(60) , Sizing.fill(60))
                        .child(Containers.horizontalFlow(Sizing.fill(10) , Sizing.fill(10)))
                        .padding(Insets.of(10)) //
                        .surface(Surface.DARK_PANEL)
                        .verticalAlignment(VerticalAlignment.CENTER)
                        .horizontalAlignment(HorizontalAlignment.CENTER)
        );
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)){
            return true;

        }

        if ( ModKeybindings.talentTreeKey.matchesKey(keyCode,scanCode)) {
            this.close();
            return true;
        }
        return false;
    }
}
