package com.example.dmytroberezhnyi_vdatatesttask.adapters

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyViewHolder.CompanySize
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyViewHolder.OnCompanyItemLongPressedListener
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import kotlinx.android.synthetic.main.company_item.view.*
import java.util.*

class CompanyRecyclerAdapter(
    private val companySize: CompanySize,
    private val listener: OnCompanyItemLongPressedListener? = null
) : RecyclerView.Adapter<CompanyViewHolder>() {

    private val items = ArrayList<Company>()

    fun setItems(items: List<Company>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.company_item, parent, false)
        return CompanyViewHolder(view, companySize, listener)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class CompanyViewHolder(
    itemView: View, private val companySize: CompanySize,
    listener: OnCompanyItemLongPressedListener?
) : RecyclerView.ViewHolder(itemView) {

    init {
        listener?.let {
            itemView.setOnLongClickListener {
                listener.onCompanyItemLongPressed(company)
                true
            }
        }
    }

    lateinit var company: Company

    fun bind(company: Company) {
        this.company = company
        itemView.tvCompanyName.text = company.companyName

        var cardViewHeight = 0
        if (companySize == CompanySize.SMALL) {
            cardViewHeight = 25
        } else if (companySize == CompanySize.NORMAL) {
            cardViewHeight = 50
        }

        itemView.cvCompany.layoutParams.height =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                cardViewHeight.toFloat(),
                itemView.context.resources.displayMetrics
            ).toInt()
    }

    interface OnCompanyItemLongPressedListener {
        fun onCompanyItemLongPressed(company: Company)
    }

    enum class CompanySize {
        SMALL,
        NORMAL
    }
}