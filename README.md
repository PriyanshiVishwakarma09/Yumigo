# 🚚 Yumigo — My Orders Screen

A native Android application built with **Kotlin + XML** that implements the **"My Orders"** screen UI as part of an internship assignment. The screen displays a list of past orders with filtering, search, and action capabilities.

---

## 📱 Screenshots

| My Orders Screen |
|:---:|
| *The main screen showing the yellow header, search bar, filter tabs, info banner, and scrollable order cards with bottom navigation.* |

---

## ✨ Features

### UI Components
- **Yellow Gradient Header** — Displays the "My Orders" title, subtitle description, and a truck illustration with a location pin icon
- **Search Bar** — Search field with placeholder text "Search by Order ID / Location", along with Filter and Sort action buttons
- **Filter Tabs** — Horizontally scrollable pill-shaped tabs: `All Orders`, `Completed`, `Cancelled`, `Booked Again` with proper selection states
- **Info Banner** — Dismissible information banner with Hindi text and a close button
- **Order Cards (RecyclerView)** — Each card displays:
  - Vehicle type with truck icon
  - Date, time, and Order ID
  - Pickup location (green dot indicator)
  - Drop-off location (red dot indicator)
  - Price in ₹ (Indian Rupees)
  - Order status badge (CANCELLED / COMPLETED / BOOKED AGAIN)
  - Three-dot overflow menu
  - "Invoice" button with download icon
  - "Book Again" action button
- **Help FAB** — Floating action button with headphone icon and "Help" label positioned above the bottom navigation
- **Bottom Navigation Bar** — 4 tabs: Home, Orders (selected), Payments, Account with proper icon tinting

### Functional Features
- **Tab Filtering** — Clicking on tabs dynamically filters the order list by status (All / Completed / Cancelled / Booked Again)
- **Tab Selection State** — Selected tab has a dark pill background with white text; unselected tabs have an outlined pill style
- **Dismissible Info Banner** — Close button hides the info banner
- **Ripple Click Effects** — All buttons, tabs, and interactive elements have proper Material ripple touch feedback
- **Bottom Navigation** — Functional navigation with toast messages for tab switching
- **Click Handlers** — Invoice, Book Again, and More options buttons show toast messages on click

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| **Language** | Kotlin |
| **UI** | XML Layouts |
| **Architecture** | Activity + ViewBinding |
| **List Rendering** | RecyclerView with custom Adapter |
| **Navigation** | Material BottomNavigationView |
| **Build System** | Gradle (Kotlin DSL) |
| **Min SDK** | 26 (Android 8.0 Oreo) |
| **Target SDK** | 35 (Android 15) |

> ⚠️ **No cross-platform frameworks** (Flutter, React Native, etc.) are used. This is a **100% native Android** implementation using **Kotlin + XML**.

---

## 📂 Project Structure

```
Yumigo/
├── app/
│   ├── build.gradle.kts                    # App-level build config (ViewBinding, dependencies)
│   └── src/main/
│       ├── AndroidManifest.xml             # App manifest
│       ├── java/com/example/yumigo/
│       │   ├── MainActivity.kt             # Main activity with tab logic, RecyclerView setup
│       │   ├── adapter/
│       │   │   └── OrderAdapter.kt         # RecyclerView adapter for order items
│       │   └── model/
│       │       └── Order.kt                # Data class & OrderStatus enum
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml       # Main screen layout
│           │   └── item_order.xml          # Individual order card layout
│           ├── drawable/                   # Vector icons, shape backgrounds, ripples
│           │   ├── ic_truck.xml            # Truck vehicle icon
│           │   ├── ic_home.xml             # Home nav icon
│           │   ├── ic_orders.xml           # Orders nav icon (clock)
│           │   ├── ic_payments.xml         # Payments nav icon (envelope)
│           │   ├── ic_account.xml          # Account nav icon (person)
│           │   ├── ic_search.xml           # Search icon
│           │   ├── ic_filter.xml           # Filter funnel icon
│           │   ├── ic_sort.xml             # Sort icon
│           │   ├── ic_download.xml         # Download/Invoice icon
│           │   ├── ic_more_vert.xml        # Three-dot menu icon
│           │   ├── ic_close.xml            # Close/dismiss icon
│           │   ├── ic_info.xml             # Info circle icon
│           │   ├── ic_headphones.xml       # Help/headphone icon
│           │   ├── ic_location_pin.xml     # Red location pin
│           │   ├── dot_green.xml           # Green circle (pickup)
│           │   ├── dot_red.xml             # Red circle (drop-off)
│           │   ├── bg_header.xml           # Yellow gradient header background
│           │   ├── bg_search_bar.xml       # Rounded search bar background
│           │   ├── bg_tab_selected.xml     # Dark pill for selected tab
│           │   ├── bg_tab_unselected.xml   # Outlined pill for unselected tab
│           │   ├── bg_tab_*_ripple.xml     # Ripple wrappers for tabs
│           │   ├── bg_button_outline.xml   # Outline button background
│           │   ├── bg_button_outline_ripple.xml  # Ripple for buttons
│           │   ├── bg_status_cancelled.xml # Light red badge background
│           │   ├── bg_status_completed.xml # Light green badge background
│           │   ├── bg_info_banner.xml      # Light blue banner background
│           │   └── bg_help_fab.xml         # Yellow circular FAB background
│           ├── menu/
│           │   └── bottom_nav_menu.xml     # Bottom navigation menu items
│           ├── color/
│           │   └── bottom_nav_color.xml    # Color state list (selected/unselected)
│           └── values/
│               ├── colors.xml              # Color palette
│               ├── strings.xml             # All string resources (English + Hindi)
│               ├── dimens.xml              # Spacing, text sizes, corner radii
│               └── themes.xml              # Material Components theme
├── build.gradle.kts                        # Root build config
├── gradle/
│   └── libs.versions.toml                  # Version catalog for dependencies
├── settings.gradle.kts                     # Project settings
└── README.md                               # This file
```

---

## 🏗️ Architecture & Design Decisions

### ViewBinding
- Enabled in `build.gradle.kts` via `buildFeatures { viewBinding = true }`
- Provides type-safe view references without `findViewById()` in the Activity

### RecyclerView with Custom Adapter
- `OrderAdapter` extends `RecyclerView.Adapter<OrderViewHolder>`
- Each order item is inflated from `item_order.xml`
- Status-based styling is applied dynamically in `onBindViewHolder()` (different colors for CANCELLED vs COMPLETED vs BOOKED AGAIN)
- `updateOrders()` method enables dynamic list filtering

### Data Model
```kotlin
data class Order(
    val id: String,
    val vehicleType: String,
    val dateTime: String,
    val orderId: String,
    val pickupLocation: String,
    val dropLocation: String,
    val price: Double,
    val status: OrderStatus
)

enum class OrderStatus {
    CANCELLED, COMPLETED, BOOKED_AGAIN
}
```

### Tab Filtering Logic
- Tabs act as filters: clicking a tab calls `filterOrders()` which filters `allOrders` by `OrderStatus`
- Selected tab styling updates via `selectTab()` which toggles background drawables and text colors

### Material Design
- Uses `Theme.MaterialComponents.Light.NoActionBar` as the base theme
- `BottomNavigationView` from Material Components with `labelVisibilityMode="labeled"`
- Color state list selector for bottom nav icon/text tinting
- Ripple effects on all interactive elements via `<ripple>` drawables and `?attr/selectableItemBackgroundBorderless`

---

## 📦 Dependencies

| Library | Version | Purpose |
|---|---|---|
| `androidx.core:core-ktx` | 1.18.0 | Kotlin extensions for Android |
| `androidx.appcompat:appcompat` | 1.7.0 | Backward-compatible Activity & theme |
| `com.google.android.material:material` | 1.12.0 | BottomNavigationView, Material theme |
| `androidx.constraintlayout:constraintlayout` | 2.2.1 | Flexible layout system |
| `androidx.recyclerview:recyclerview` | 1.4.0 | Efficient scrollable list |
| `androidx.cardview:cardview` | 1.0.0 | Card UI component |
| `junit:junit` | 4.13.2 | Unit testing |
| `androidx.test.ext:junit` | 1.3.0 | Android test extensions |
| `androidx.test.espresso:espresso-core` | 3.7.0 | UI testing |

---

## 🚀 How to Run

### Prerequisites
- **Android Studio** (Hedgehog 2023.1.1 or later recommended)
- **JDK 11** or higher
- **Android SDK** with API Level 35 installed

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/PriyanshiVishwakarma09/Yumigo.git
   cd Yumigo
   ```

2. **Open in Android Studio**
   - Open Android Studio → File → Open → select the `Yumigo` folder
   - Wait for Gradle sync to complete

3. **Run the app**
   - Connect a physical device or start an emulator (API 26+)
   - Click ▶️ **Run** or press `Shift + F10`
   - The "My Orders" screen will launch as the main screen

### Build from Command Line
```bash
./gradlew assembleDebug
```
The APK will be generated at:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## ✅ Assignment Checklist

| Requirement | Status |
|---|:---:|
| Developed in **Android Native (Kotlin + XML)** | ✅ |
| No Flutter, React Native, or other frameworks | ✅ |
| Design closely matches the provided reference | ✅ |
| Proper spacing, alignment, and clean UI | ✅ |
| Responsive layout | ✅ |
| **RecyclerView** for order listing | ✅ |
| **Bottom Navigation** included | ✅ |
| Buttons/tabs have proper **click/ripple effects** | ✅ |
| Code is **clean and properly structured** | ✅ |
| Separated concerns: Model, Adapter, Activity | ✅ |
| All icons as XML vector drawables (no PNGs) | ✅ |
| String resources externalized in `strings.xml` | ✅ |
| Color palette defined in `colors.xml` | ✅ |
| Dimensions defined in `dimens.xml` | ✅ |

---

## 🎨 UI Design Highlights

- **Yellow gradient header** (#FFC107 → #FFD54F) matches the brand theme
- **Pill-shaped filter tabs** with dark selected state and outlined unselected state
- **Green/Red dot indicators** for pickup and drop-off locations
- **Status badges** with semantic colors (red for cancelled, green for completed)
- **Outline buttons** with ripple feedback for Invoice and Book Again actions
- **Material BottomNavigationView** with yellow active state and gray inactive state
- **Floating Help button** with headphone icon positioned above the bottom nav
- **Hindi language support** in the info banner for localization readiness
- **NestedScrollView** wrapping the entire content for smooth scrolling with RecyclerView

---

## 👩‍💻 Author

**Priyanshi Vishwakarma**

---
