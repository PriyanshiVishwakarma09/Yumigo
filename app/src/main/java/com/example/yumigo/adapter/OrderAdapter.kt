package com.example.yumigo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yumigo.R
import com.example.yumigo.model.Order
import com.example.yumigo.model.OrderStatus

class OrderAdapter(
    private var orders: List<Order>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvVehicleType: TextView = itemView.findViewById(R.id.tv_vehicle_type)
        val tvDateOrderId: TextView = itemView.findViewById(R.id.tv_date_order_id)
        val tvPickup: TextView = itemView.findViewById(R.id.tv_pickup)
        val tvDrop: TextView = itemView.findViewById(R.id.tv_drop)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        val tvStatus: TextView = itemView.findViewById(R.id.tv_status)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more)
        val btnInvoice: LinearLayout = itemView.findViewById(R.id.btn_invoice)
        val btnBookAgain: LinearLayout = itemView.findViewById(R.id.btn_book_again)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        val context = holder.itemView.context

        holder.tvVehicleType.text = order.vehicleType
        holder.tvDateOrderId.text = "${order.dateTime}  |  Order ID: #${order.orderId}"
        holder.tvPickup.text = order.pickupLocation
        holder.tvDrop.text = order.dropLocation
        holder.tvPrice.text = "₹ ${order.price}"

        // Set status
        when (order.status) {
            OrderStatus.CANCELLED -> {
                holder.tvStatus.text = "CANCELLED"
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.status_cancelled_text))
                holder.tvStatus.setBackgroundResource(R.drawable.bg_status_cancelled)
            }
            OrderStatus.COMPLETED -> {
                holder.tvStatus.text = "COMPLETED"
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.status_completed_text))
                holder.tvStatus.setBackgroundResource(R.drawable.bg_status_completed)
            }
            OrderStatus.BOOKED_AGAIN -> {
                holder.tvStatus.text = "BOOKED AGAIN"
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.status_completed_text))
                holder.tvStatus.setBackgroundResource(R.drawable.bg_status_completed)
            }
        }

        // Click listeners
        holder.btnMore.setOnClickListener {
            Toast.makeText(context, "More options for ${order.orderId}", Toast.LENGTH_SHORT).show()
        }

        holder.btnInvoice.setOnClickListener {
            Toast.makeText(context, "Download invoice for ${order.orderId}", Toast.LENGTH_SHORT).show()
        }

        holder.btnBookAgain.setOnClickListener {
            Toast.makeText(context, "Book again for ${order.orderId}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = orders.size

    fun updateOrders(newOrders: List<Order>) {
        orders = newOrders
        notifyDataSetChanged()
    }
}
