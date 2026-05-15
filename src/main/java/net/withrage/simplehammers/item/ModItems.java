package net.withrage.simplehammers.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.withrage.simplehammers.SimpleHammers;
import net.withrage.simplehammers.config.SimpleHammersConfig;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimpleHammers.MOD_ID);
    public static final RegistryObject<Item> WOODEN_HAMMER = registerHammer("wooden_hammer", ModToolMaterial.WOOD, 1, -3.2F, SimpleHammersConfig.woodenDurability, false);
    public static final RegistryObject<Item> STONE_HAMMER = registerHammer("stone_hammer", ModToolMaterial.STONE, 1, -3.1F, SimpleHammersConfig.stoneDurability, false);
    public static final RegistryObject<Item> COPPER_HAMMER = registerHammer("copper_hammer", ModToolMaterial.COPPER, 1, -2.9F, SimpleHammersConfig.copperDurability, false);
    public static final RegistryObject<Item> GOLDEN_HAMMER = registerHammer("golden_hammer", ModToolMaterial.GOLD, 1, -2.7F, SimpleHammersConfig.goldenDurability, false);
    public static final RegistryObject<Item> IRON_HAMMER = registerHammer("iron_hammer", ModToolMaterial.IRON, 2, -3.0F, SimpleHammersConfig.ironDurability, false);
    public static final RegistryObject<Item> EMERALD_HAMMER = registerHammer("emerald_hammer", ModToolMaterial.EMERALD, 3, -2.5F, SimpleHammersConfig.emeraldDurability, false);
    public static final RegistryObject<Item> DIAMOND_HAMMER = registerHammer("diamond_hammer", ModToolMaterial.DIAMOND, 3, -2.6F, SimpleHammersConfig.diamondDurability, false);
    public static final RegistryObject<Item> NETHERITE_HAMMER = registerHammer("netherite_hammer", ModToolMaterial.NETHERITE, 4, -2.4F, SimpleHammersConfig.netheriteDurability, true);

    public static final RegistryObject<Item> WOODEN_HANDLE = registerSimpleItem("wooden_handle");
    public static final RegistryObject<Item> WOODEN_HAMMER_HEAD = registerSimpleItem("wooden_hammer_head");
    public static final RegistryObject<Item> STONE_HAMMER_HEAD = registerSimpleItem("stone_hammer_head");
    public static final RegistryObject<Item> COPPER_HAMMER_HEAD = registerSimpleItem("copper_hammer_head");
    public static final RegistryObject<Item> GOLDEN_HAMMER_HEAD = registerSimpleItem("golden_hammer_head");
    public static final RegistryObject<Item> IRON_HAMMER_HEAD = registerSimpleItem("iron_hammer_head");
    public static final RegistryObject<Item> EMERALD_HAMMER_HEAD = registerSimpleItem("emerald_hammer_head");
    public static final RegistryObject<Item> DIAMOND_HAMMER_HEAD = registerSimpleItem("diamond_hammer_head");

    public static final RegistryObject<Item> HAMMER_ADV_TROPHY = registerSimpleItem("all_hammers");

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(SimpleHammers.MOD_ID, name)
        );
    }

    private static RegistryObject<Item> registerSimpleItem(String name) {
        return ITEMS.register(name, () -> new Item(
                new Item.Properties().setId(itemKey(name))
        ));
    }

    private static RegistryObject<Item> registerHammer(String name,
                                                     ToolMaterial material,
                                                     int attackDamage,
                                                     float attackSpeed,
                                                     int durability,
                                                     boolean fireproof) {

        return ITEMS.register(name, () -> {
            Item.Properties properties = new Item.Properties().setId(itemKey(name)).durability(durability);

            if (fireproof) {
                properties.fireResistant();
            }

            return new HammerItem(material, attackDamage, attackSpeed, durability, properties);
        });
    }

    public static void register(BusGroup eventBus) {
        ITEMS.register(eventBus);
    }
}