package net.bobbacon.races.item;

import com.google.common.collect.Multimap;
import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.player.IItemMixin;
import net.bobbacon.races.player.IPlayerEntityMixin;
import net.bobbacon.races.player.WeaponTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.spell_engine.api.item.weapon.SpellWeaponItem;
import net.spell_engine.api.spell.ExternalSpellSchools;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.SpellContainer;
import net.spell_engine.internals.SpellContainerHelper;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;

public class OneHandedWarAxe extends SpellWeaponItem {
    SpellSchool school = ExternalSpellSchools.PHYSICAL_MELEE;

    public OneHandedWarAxe(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    public OneHandedWarAxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, SpellSchool school) {
        super(material, attackDamage, attackSpeed, settings);
        this.school=school;

        IItemMixin item=(IItemMixin)this;
        item.setWeaponType(WeaponTypes.AXE);

    }
    public SpellSchool getSchool() {
        return school;
    }
    private Multimap<EntityAttribute, EntityAttributeModifier> attributes;

    public void setAttributes(Multimap<EntityAttribute, EntityAttributeModifier> attributes) {
        this.attributes = attributes;
    }
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (this.attributes == null) {
            return super.getAttributeModifiers(slot);
        } else {
            return slot == EquipmentSlot.MAINHAND ? this.attributes : super.getAttributeModifiers(slot);
        }
    }
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.SKELETON_SKULL)) {
            return 5F;
        } else {
            return state.isIn(BlockTags.AXE_MINEABLE) ? 1.5F : 1.0F;
        }
    }

    public boolean isSuitableFor(BlockState state) {
        return state.isIn(BlockTags.LOGS);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        stack.damage(3,miner,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        ((IPlayerEntityMixin)miner).learn(new Identifier(RacesModForMyServer.MOD_ID,"simple_spin"));

        return super.postMine(stack, world, state, pos, miner);
    }
}
