package net.bobbacon.weapon_crafting_overhaul.block.block_entity;

import net.bobbacon.weapon_crafting_overhaul.block.ModBlocks;
import net.bobbacon.weapon_crafting_overhaul.registry.RegistryHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBE {
    private static final RegistryHelper<BlockEntityType<?>> registryHelper= new RegistryHelper<>(Registries.BLOCK_ENTITY_TYPE);
//    public static final BlockEntityType<BrickFurnaceBE> BRICK_FURNACE_BE_T = registryHelper.register("brick_furnace_be",BlockEntityType.Builder.create(BrickFurnaceBE::new, ModBlocks.brick_furnace).build());
    public static final BlockEntityType<BrickFurnaceBE> BRICK_FURNACE_BLOCK_ENTITY = Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        new Identifier("tutorial", "demo_block_entity"),
        FabricBlockEntityTypeBuilder.create(BrickFurnaceBE::new, ModBlocks.MUD_OVEN).build(null)
    );
    public static void init(){

    }
}
