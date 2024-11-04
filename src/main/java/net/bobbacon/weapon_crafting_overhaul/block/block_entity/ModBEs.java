package net.bobbacon.weapon_crafting_overhaul.block.block_entity;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.block.ModBlocks;
import net.bobbacon.weapon_crafting_overhaul.registry.RegistryHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBEs {
    private static final RegistryHelper<BlockEntityType<?>> registryHelper= new RegistryHelper<>(Registries.BLOCK_ENTITY_TYPE);
//    public static final BlockEntityType<BrickFurnaceBE> BRICK_FURNACE_BE_T = registryHelper.register("brick_furnace_be",BlockEntityType.Builder.create(BrickFurnaceBE::new, ModBlocks.brick_furnace).build());
    public static final BlockEntityType<BrickFurnaceBE> MUD_OVEN_BLOCK_ENTITY_TYPE = Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        new Identifier(WeaponCraftingOverhaul.MOD_ID, "brick_furnace_block_entity"),
        FabricBlockEntityTypeBuilder.create(BrickFurnaceBE::new, ModBlocks.MUD_OVEN).build(null)
    );
    public static final BlockEntityType<BellowsBE> BELLOWS_BLOCK_ENTITY_TYPE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(WeaponCraftingOverhaul.MOD_ID,"bellows_block_entity"),
            FabricBlockEntityTypeBuilder.create(BellowsBE::new, ModBlocks.BELLOWS).build());
    public static final BlockEntityType<SmithingAnvilBE> SMITHING_ANVIL_BLOCK_ENTITY_TYPE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            new Identifier(WeaponCraftingOverhaul.MOD_ID,"basic_smithing_anvil"),
            FabricBlockEntityTypeBuilder.create(SmithingAnvilBE::new,ModBlocks.BASIC_SMITHING_ANVIL).build());
    public static void init(){

    }
}
