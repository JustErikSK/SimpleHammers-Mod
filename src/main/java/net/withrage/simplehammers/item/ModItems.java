package net.withrage.simplehammers.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraftforge.eventbus.api.IEventBus;
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

    public static final RegistryObject<Item> WOODEN_HANDLE = ITEMS.register("wooden_handle", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_HAMMER_HEAD = ITEMS.register("wooden_hammer_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STONE_HAMMER_HEAD = ITEMS.register("stone_hammer_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_HAMMER_HEAD = ITEMS.register("copper_hammer_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_HAMMER_HEAD = ITEMS.register("golden_hammer_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_HAMMER_HEAD = ITEMS.register("iron_hammer_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_HAMMER_HEAD = ITEMS.register("emerald_hammer_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_HAMMER_HEAD = ITEMS.register("diamond_hammer_head", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HAMMER_ADV_TROPHY = ITEMS.register("all_hammers", () -> new Item(new Item.Properties()));

    private static RegistryObject<Item> registerHammer(String name,
                                                     Tier material,
                                                     int attackDamage,
                                                     float attackSpeed,
                                                     int durability,
                                                     boolean fireproof) {

        return ITEMS.register(name, () -> {
            Item.Properties properties = new Item.Properties().stacksTo(1);

            if (fireproof) {
                properties.fireResistant();
            }

            return new HammerItem(material, attackDamage, attackSpeed, durability, properties);
        });
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}