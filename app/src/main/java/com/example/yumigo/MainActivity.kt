package com.example.yumigo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumigo.adapter.OrderAdapter
import com.example.yumigo.databinding.ActivityMainBinding
import com.example.yumigo.model.Order
import com.example.yumigo.model.OrderStatus

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var orderAdapter: OrderAdapter
    private var allOrders: List<Order> = emptyList()
    private var selectedTab: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupStatusBar()
        setupRecyclerView()
        setupTabs()
        setupBottomNavigation()
        setupInfoBanner()
        setupHelpFab()
        loadSampleData()
    }

    private fun setupStatusBar() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow_primary)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun setupRecyclerView() {
        orderAdapter = OrderAdapter(emptyList())
        binding.recyclerOrders.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = orderAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun setupTabs() {
        val tabs = listOf(
            binding.tabAllOrders,
            binding.tabCompleted,
            binding.tabCancelled,
            binding.tabBookedAgain
        )

        // Set initial selected tab
        selectedTab = binding.tabAllOrders

        tabs.forEach { tab ->
            tab.setOnClickListener {
                selectTab(tab, tabs)
                filterOrders(tab)
            }
        }
    }

    private fun selectTab(selected: TextView, allTabs: List<TextView>) {
        allTabs.forEach { tab ->
            if (tab == selected) {
                tab.setBackgroundResource(R.drawable.bg_tab_selected_ripple)
                tab.setTextColor(ContextCompat.getColor(this, R.color.tab_selected_text))
            } else {
                tab.setBackgroundResource(R.drawable.bg_tab_unselected_ripple)
                tab.setTextColor(ContextCompat.getColor(this, R.color.tab_unselected_text))
            }
        }
        selectedTab = selected
    }

    private fun filterOrders(tab: TextView) {
        val filtered = when (tab.id) {
            R.id.tab_all_orders -> allOrders
            R.id.tab_completed -> allOrders.filter { it.status == OrderStatus.COMPLETED }
            R.id.tab_cancelled -> allOrders.filter { it.status == OrderStatus.CANCELLED }
            R.id.tab_booked_again -> allOrders.filter { it.status == OrderStatus.BOOKED_AGAIN }
            else -> allOrders
        }
        orderAdapter.updateOrders(filtered)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.nav_orders
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_orders -> true
                R.id.nav_payments -> {
                    Toast.makeText(this, "Payments", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_account -> {
                    Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupInfoBanner() {
        binding.btnCloseBanner.setOnClickListener {
            binding.infoBanner.visibility = View.GONE
        }
    }

    private fun setupHelpFab() {
        binding.fabHelp.setOnClickListener {
            Toast.makeText(this, "Help & Support", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadSampleData() {
        allOrders = listOf(
            Order(
                id = "1",
                vehicleType = "Four Wheeler",
                dateTime = "05 Feb, 4:46 PM",
                orderId = "ORD12345",
                pickupLocation = "741, Gumanwara",
                dropLocation = "00, Main Rd, Shivaji Nagar, Jhansi, Uttar Pradesh 284001, India",
                price = 229.0,
                status = OrderStatus.CANCELLED
            ),
            Order(
                id = "2",
                vehicleType = "Four Wheeler",
                dateTime = "05 Feb, 4:46 PM",
                orderId = "ORD12346",
                pickupLocation = "741, Gumanwara",
                dropLocation = "00, Main Rd, Shivaji Nagar, Jhansi, Uttar Pradesh 284001, India",
                price = 229.0,
                status = OrderStatus.CANCELLED
            ),
            Order(
                id = "3",
                vehicleType = "Four Wheeler",
                dateTime = "05 Feb, 4:46 PM",
                orderId = "ORD12347",
                pickupLocation = "332, Gumanwara",
                dropLocation = "GC72+GGV, Kamrari, Madhya Pradesh 475661, India",
                price = 1515.0,
                status = OrderStatus.CANCELLED
            ),
            Order(
                id = "4",
                vehicleType = "Four Wheeler",
                dateTime = "05 Feb, 4:46 PM",
                orderId = "ORD12348",
                pickupLocation = "332, Gumanwara",
                dropLocation = "GC72+GGV, Kamrari, Madhya Pradesh 475661, India",
                price = 1634.0,
                status = OrderStatus.CANCELLED
            ),
            Order(
                id = "5",
                vehicleType = "Four Wheeler",
                dateTime = "04 Feb, 2:30 PM",
                orderId = "ORD12340",
                pickupLocation = "221, Sector 5",
                dropLocation = "45, MG Road, Gwalior, Madhya Pradesh 474001, India",
                price = 899.0,
                status = OrderStatus.COMPLETED
            ),
            Order(
                id = "6",
                vehicleType = "Four Wheeler",
                dateTime = "03 Feb, 10:15 AM",
                orderId = "ORD12339",
                pickupLocation = "112, Civil Lines",
                dropLocation = "78, Station Road, Jhansi, Uttar Pradesh 284001, India",
                price = 345.0,
                status = OrderStatus.BOOKED_AGAIN
            )
        )
        orderAdapter.updateOrders(allOrders)
    }
}