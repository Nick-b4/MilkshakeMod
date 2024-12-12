package net.milkshake.milkshakemod.screen; 

import net.milkshake.milkshakemod.MilkshakeMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MilkshakeMod.Mod_ID);

    public static final RegistryObject<MenuType<ChaliceOfDeceptionMenu>> CHALICE_OF_DECEPTION_MENU =
            registerMenuType("chalice_of_deception_menu", ChaliceOfDeceptionMenu::new);

    public static final RegistryObject<MenuType<ChaliceOfEternalFlameMenu>> CHALICE_OF_ETERNAL_FLAME_MENU =
            MENUS.register("chalice_of_eternal_flame_menu",
                    () -> IForgeMenuType.create(ChaliceOfEternalFlameMenu::new));

    public static final RegistryObject<MenuType<ChaliceOfVoidMenu>> CHALICE_OF_VOID_MENU =
            registerMenuType("chalice_of_void_menu", ChaliceOfVoidMenu::new);

    public static final RegistryObject<MenuType<ChaliceOfNatureMenu>> CHALICE_OF_NATURE_MENU =
            registerMenuType("chalice_of_nature_menu", ChaliceOfNatureMenu::new);

    public static final RegistryObject<MenuType<CursedIcePedestalMenu>> CURSED_ICE_PEDESTAL_MENU =
            registerMenuType("cursed_ice_pedestal_menu", CursedIcePedestalMenu::new);

    public static final RegistryObject<MenuType<FestiveSpireMenu>> FESTIVE_SPIRE_MENU =
            registerMenuType("festive_spire_menu", FestiveSpireMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}