/*
 * This file is part of Agate.
 *
 * Agate is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Agate is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Agate.  If not, see <https://www.gnu.org/licenses/>.
 */

package item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    /**
     * The item's Material type
     */
    private final @NonNull Material type;

    /**
     * The item's name (as an adventure Component)
     */
    private final @Nullable Component name;

    /**
     * The item's lore (as adventure Components)
     */
    private final @Nullable List<Component> lore;

    /**
     * Constructs the ItemBuilder
     * @param type Material type
     */
    public ItemBuilder(final @NonNull Material type) {
        this.type = type;
        this.name = null;
        this.lore = null;
    }

    /**
     * Builds the ItemStack and returns it
     * @return new ItemStack
     */
    public ItemStack build() {
        final @NonNull ItemStack itemStack = new ItemStack(type);
        final @NonNull ItemMeta itemMeta = Bukkit.getItemFactory().getItemMeta(type);

        final @NonNull BungeeComponentSerializer bungeeComponentSerializer = BungeeComponentSerializer.get();

        // add name
        if (name != null) {
            itemMeta.setDisplayNameComponent(bungeeComponentSerializer.serialize(name));
        }

        // add lore
        if (lore != null && !lore.isEmpty()) {
            final @NonNull List<BaseComponent[]> loreComponentArray = new ArrayList<>();

            for (final @NonNull Component component : lore) {
                loreComponentArray.add(bungeeComponentSerializer.serialize(component));
            }

            itemMeta.setLoreComponents(loreComponentArray);
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}
