package com.achmadrizkin.achmadrizki_suitmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.achmadrizkin.achmadrizki_suitmedia.R
import com.achmadrizkin.achmadrizki_suitmedia.data.model.UserResponse
import com.bumptech.glide.Glide

class RecyclerViewUserAdapter :
    RecyclerView.Adapter<RecyclerViewUserAdapter.AdsViewHolder>() {

    var listUser: List<UserResponse>? = null
    private var itemClickListener: ItemClickListener? = null

    fun setListDataItems(listUser: List<UserResponse>) {
        this.listUser = listUser
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view, parent, false)
        return AdsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        listUser?.let { holder.setupData(it, position) }
        holder.clUser.setOnClickListener(View.OnClickListener { view: View? ->
            if (itemClickListener != null && listUser != null) {
                itemClickListener!!.onClick(
                    holder.clUser,
                    listUser!!,
                    holder.adapterPosition
                )
            }
        })
    }

    override fun getItemCount(): Int {
        if (listUser != null) {
            return listUser!!.size
        } else {
            return 0;
        }
    }

    class AdsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val ivProducts: ImageView
        val tvUserMain: TextView
        val tvUserDetails: TextView
        val clUser: ConstraintLayout

        init {
            //
            ivProducts = itemView.findViewById(R.id.imageView)
            tvUserMain = itemView.findViewById(R.id.tvUserMain)
            clUser = itemView.findViewById(R.id.clUser)
            tvUserDetails = itemView.findViewById(R.id.tvUserDetails)
        }

        fun setupData(
            listUserResponse: List<UserResponse>,
            position: Int
        ) {
            //
            tvUserMain.text = listUserResponse[position].first_name + " " + listUserResponse[position].last_name
            tvUserDetails.text = listUserResponse[position].email

            //
            setupImage(listUserResponse, position)
        }

        private fun setupImage(listUserResponse: List<UserResponse>, position: Int) {
            Glide.with(ivProducts.context).load(listUserResponse[position].avatar).into(ivProducts)
        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onClick(
            view: View?,
            listUserResponse2: List<UserResponse>,
            position: Int
        )
    }
}
