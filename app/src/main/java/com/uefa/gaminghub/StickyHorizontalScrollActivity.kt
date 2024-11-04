package com.uefa.gaminghub

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uefa.gaminghub.databinding.ActivityStickyHorizontalScrollBinding
import com.uefa.gaminghub.databinding.ItemHeadingBinding
import com.uefa.gaminghub.databinding.ItemTeamBinding


@SuppressLint("ClickableViewAccessibility")
class StickyHorizontalScrollActivity : AppCompatActivity() {

    private val list by lazy {
        listOf(
            ListingData(
                "First",
                listOf(
                    "First Item1",
                    "First Item2",
                    "First Item3",
                    "First Item4",
                    "First Item5",
                    "First Item6",
                    "First Item7",
                    "First Item8"
                )
            ),
            ListingData("Second", listOf("Item1", "Item2")),
            ListingData("Third", listOf("Item1", "Item2")),
            ListingData("Fourth", listOf("Item1", "Item2")),
            ListingData("Fifth", listOf("Item1", "Item2"))
        )
    }

    lateinit var _binding: ActivityStickyHorizontalScrollBinding
    val binding get() = _binding
    lateinit var firstItemTextView:View

    val headingAdapter by lazy {
        SimpleListAdapter(
            inflate = ItemHeadingBinding::inflate,
            itemComparator = object : DiffUtil.ItemCallback<ListingData>() {
                override fun areItemsTheSame(oldItem: ListingData, newItem: ListingData): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: ListingData,
                    newItem: ListingData
                ): Boolean {
                    return oldItem.title == newItem.title
                }


            },
            onBind = { position, rowBinding, map ->
                rowBinding.matchTitle.apply {
                    text = map.title
                    if(position == 0){
                        firstItemTextView = rowBinding.matchTitle
                    }
                }

                val subDataAdapter = SimpleListAdapter(
                    inflate = ItemTeamBinding::inflate,
                    itemComparator = object : DiffUtil.ItemCallback<String>() {
                        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                            return oldItem == newItem
                        }

                        override fun areContentsTheSame(
                            oldItem: String,
                            newItem: String
                        ): Boolean {
                            return oldItem == newItem
                        }


                    },
                    onBind = { position, rowBinding, map ->
                        rowBinding.tvTeam.text = map
                    }
                )

                val itemList = map.subList
                rowBinding.rvListing.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(false)
                    adapter = subDataAdapter

//                    setOnTouchListener { v, event ->
//                        when (event?.action) {
//                            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_DOWN, MotionEvent.ACTION_UP -> {
//                                binding.rvHeading.requestDisallowInterceptTouchEvent(true);
//                            }
//
//                            else -> {
//                                binding.rvHeading.requestDisallowInterceptTouchEvent(false);
//                            }
//                        }
//                        return@setOnTouchListener false
//                    }
                }
                subDataAdapter.submitList(itemList)
            }
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_sticky_horizontal_scroll)

        binding.rvHeading.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = headingAdapter

            var initialFirstPos = -1
            var itemsHiddenFromLeft: Int = 0
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                    val currFirstPos = layoutManager.findFirstVisibleItemPosition()
//
                    if (initialFirstPos == -1) {
                        initialFirstPos = currFirstPos
                    }
//
                    itemsHiddenFromLeft = (currFirstPos - initialFirstPos).coerceAtLeast(0)

                    // Adjust the X position of the TextView based on scroll
                    firstItemTextView.x = (-layoutManager.findViewByPosition(currFirstPos)!!.left).toFloat()

                    // Optional: If you want to stop moving the TextView when the last item is fully hidden
                    if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                        val lastItem = layoutManager.findViewByPosition(layoutManager.itemCount - 1)
                        if (lastItem?.right ?: 0 <= recyclerView.width) {
                            firstItemTextView.x = 0f
                        }
                    }
                }
            })
        }

        headingAdapter.submitList(list)
    }
}

data class ListingData(
    val title: String,
    val subList: List<String>
)