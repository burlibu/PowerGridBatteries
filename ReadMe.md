# Create: Power Grid Batteries

**Create: Power Grid Batteries** is an **unofficial add-on** for the **Create: Power Grid** mod (Minecraft 1.21.1 on NeoForge).
It introduces 4 tiers of high-performance batteries with scalable energy capacities and electrical specifications, seamlessly integrated into Power Grid's electrical & thermal simulation and Create's visual features (Ponder, Engineer's Goggles, and Display Links).

---

## ⚡ Key Features

- **4 Battery Tiers**: Ranging from low-voltage small batteries up to massive high-voltage substation battery banks.
- **Engineer's Goggles Integration**: Wearing Create's Engineer's Goggles and looking at any battery renders a live HUD overlay showing:
  - State of Charge (%) & Total Stored Energy (`kJ` / `MJ`).
  - Output Voltage (`V`).
  - Core Temperature (`°C`) with overheat warnings.
- **Animated Ponder Tutorials (`Hold W`)**: All add-on batteries are fully linked to Power Grid's official Ponder storyboard scenes.
- **Display Link Support**: Connect Create's Display Links to batteries to stream live energy statistics to Display Boards and screens.
- **MultiBlock Support**: Full native compatibility with Power Grid's multiblock battery mechanics, allowing adjacent battery blocks to merge into larger banks.
- **Survival Ready**:
  - Balanced block hardness (`2.0f`) for fast mining with any pickaxe.
  - Progressive shaped crafting recipes in the crafting table.
  - Safe block drops on break that retain stored energy (`Custom NBT`).
- **Dedicated Creative Tab**: Easily access all add-on items in a custom Creative tab.

---

## 📊 Technical Specifications

| Tier | Block Name | Voltage (V) | Max Current (A) | Max Power (W) | Total Capacity | Crafting Recipe Core |
|:---:|---|:---:|:---:|:---:|:---:|---|
| **Tier 1** | **Small Battery** | `12.7 V` | `8 A` | `100 W` | **10 kJ** *(10,000 J)* | PowerGrid Base Battery + 8x Copper Ingots |
| **Tier 2** | **Medium Battery** | `48.0 V` | `50 A` | `2.4 kW` *(2,400 W)* | **250 kJ** *(250,000 J)* | Small Battery + 8x Iron Ingots |
| **Tier 3** | **High-Voltage Battery** | `400.0 V` | `150 A` | `60 kW` *(60,000 W)* | **5 MJ** *(5,000,000 J)* | Medium Battery + 8x Gold Ingots |
| **Tier 4** | **Power Grid Substation** | `1000.0 V` | `500 A` | `500 kW` *(500,000 W)* | **50 MJ** *(50,000,000 J)* | High-Voltage Battery + 8x Netherite Ingots |

---

## 🛠️ Requirements

- **Minecraft**: `1.21.1`
- **Mod Loader**: `NeoForge 21.1.231+`
- **Dependencies**:
  - `Create` (`6.0.10+`)
  - `Create: Power Grid` (`0.5.6+`)

---

## 📜 License
This project is licensed under the MIT License.
