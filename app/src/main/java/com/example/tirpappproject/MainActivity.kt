package com.example.tirpappproject

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.tirpappproject.databinding.*
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    lateinit var oneFragment: OneFragment
    lateinit var twoFragment: TwoFragment
    lateinit var threeFragment: ThreeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //액션바를 툴바로 대체하기
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawer_open, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        val pagerAdapter = PagerAdapter(this)
        val title = mutableListOf<String>("홈","프로모션","예약확인")

        oneFragment = OneFragment()
        twoFragment = TwoFragment()
        threeFragment = ThreeFragment()

        pagerAdapter.addFragment(oneFragment, title[0])
        pagerAdapter.addFragment(twoFragment, title[1])
        pagerAdapter.addFragment(threeFragment, title[2])
        binding.viewpager.adapter = pagerAdapter

        //탭레이아웃 뷰페이저를 연결함
        TabLayoutMediator(binding.tablayoutMain1, binding.viewpager){tab, position ->
            tab.text = title.get(position)
        }.attach()

        //확장된 플로팅 액션기능을 확장 및 축소를 진행하기
        binding.efab.setOnClickListener {
            when(binding.efab.isExtended){
                false -> binding.efab.extend()
                true -> binding.efab.shrink()
            }
            val toast = Toast.makeText(binding.root.context, "여행을 떠나요~", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }

        // 인텐트가 있다면 헤더값 바꾸기!
        if (intent.hasExtra("id")) {
            val header = binding.navigationView.getHeaderView(0)
            header.findViewById<TextView>(R.id.naviTvName).text = "${intent.getStringExtra("id")}"
        }
        // drawerLayout navigationView 클릭이벤트 설정
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.naviMenuMember ->{
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                R.id.naviMenuReservation ->{
                    binding.viewpager.currentItem = 2
                    binding.drawerLayout.closeDrawer(Gravity.LEFT)
                    Toast.makeText(applicationContext,"예약확인 페이지로 이동합니다.", Toast.LENGTH_SHORT).show()}
                R.id.naviMenuPromotion ->{
                    binding.viewpager.currentItem = 1
                    binding.drawerLayout.closeDrawer(Gravity.LEFT)
                    Toast.makeText(applicationContext,"프로모션 페이지로 이동합니다.", Toast.LENGTH_SHORT).show()}
                R.id.naviMenuCustomer ->{
                    val items = arrayOf<String>("환불","취소","예약확인","불만접수","건의사항","업무협약","기타","상담원연결 1577-1577")
                    val eventHandler = object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, a: Int) {
                            if (a == DialogInterface.BUTTON_POSITIVE) {
                                Toast.makeText(applicationContext, "고객센터에 접수 되었습니다.", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(applicationContext, "오류발생 다시 입력바랍니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    android.app.AlertDialog.Builder(this).run {
                        setTitle("고객센터 방문목적")
                        setIcon(android.R.drawable.ic_dialog_info)
                        setMultiChoiceItems(
                            items,
                            booleanArrayOf(false, false, false, false, false, false, false, false),
                            object : DialogInterface.OnMultiChoiceClickListener {
                                override fun onClick(p0: DialogInterface?, index: Int, flag: Boolean) {
                                    // binding.tvSpending4.setText(items[index])
                                    if (flag) {
                                        Toast.makeText(
                                            applicationContext,
                                            "원할한 상담을 위해 순차적으로 연락드리겠습니다. 감사합니다.",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                }
                            })
                        setPositiveButton("닫기", eventHandler)
                        show()
                    }

                    Toast.makeText(applicationContext,"고객센터를 방문해 주셔서 감사합니다.", Toast.LENGTH_SHORT).show()}
                R.id.naviMenuClause ->{
                    val items = arrayOf<String>("이용약관에 동의 합니다.\n\n" +
                            "개인정보 처리방침의 작성\n" +
                            " 인터넷쇼핑몰 사업자는 다음의 사항이 포함된 개인정보의 처리방침(이하 \"개인정보 처리방침\"이라 함)을 정해야합니다(규제「개인정보 보호법」 제30조제1항 및 규제「개인정보 보호법 시행령」 제31조제1항).\n" +
                            " 개인정보의 처리 목적\n" +
                            " 개인정보의 처리 및 보유 기간\n" +
                            " 개인정보의 제3자 제공에 관한 사항(해당되는 경우에만 정함)\n" +
                            " 개인정보처리의 위탁에 관한 사항(해당되는 경우에만 정함)\n" +
                            " 정보주체와 법정대리인의 권리·의무 및 그 행사방법에 관한 사항\n" +
                            " 개인정보 보호책임자의 성명 또는 개인정보 보호업무 및 관련 고충사항을 처리하는 부서의 명칭과 전화번호 등 연락처\n" +
                            " 인터넷 접속정보파일 등 개인정보를 자동으로 수집하는 장치의 설치·운영 및 그 거부에 관한 사항(해당하는 경우에만 정함)\n" +
                            " 처리하는 개인정보의 항목\n" +
                            " 개인정보의 파기에 관한 사항\n" +
                            " 개인정보의 안전성 확보 조치에 관한 사항\n" +
                            "개인정보 처리방침의 공개\n" +
                            " 인터넷쇼핑몰 사업자가 개인정보 처리방침을 수립하거나 변경하는 경우에는 이용자가 쉽게 확인할 수 있도록 개인정보 처리방침을 개인정보처리자의 인터넷 홈페이지에 지속적으로 게재하여야 합니다(규제「개인정보 보호법」 제30조제2항 및 규제「개인정보 보호법 시행령」 제31조제2항).\n" +
                            " 인터넷 홈페이지에 게재할 수 없는 경우에는 다음 어느 하나 이상의 방법으로 수립하거나 변경한 개인정보 처리방침을 공개하여야 합니다(규제「개인정보 보호법」 제30조제2항 및 규제「개인정보 보호법 시행령」 제31조제3항).\n" +
                            " 개인정보처리자의 사업장등의 보기 쉬운 장소에 게시하는 방법\n" +
                            " 관보(개인정보처리자가 공공기관인 경우만 해당)나 사업장등이 있는 시·도 이상의 지역을 주된 보급지역으로 하는 일반일간신문, 일반주간신문 또는 인터넷신문에 싣는 방법\n" +
                            " 같은 제목으로 연 2회 이상 발행하여 정보주체에게 배포하는 간행물·소식지·홍보지 또는 청구서 등에 지속적으로 싣는 방법\n" +
                            " 재화나 용역을 제공하기 위하여 인터넷쇼핑몰 사업자와 이용자가 작성한 계약서 등에 실어 이용자에게 발급하는 방법\n" +
                            "위반 시 제재\n" +
                            " 개인정보 처리방침을 정하지 않거나 이를 공개하지 않으면 1천만원 이하의 과태료가 부과됩니다(「개인정보 보호법」 제75조제4항제7호). ", "동의하지 않습니다.")

                    val eventHandler = object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, answer: Int) {
                            if (answer == DialogInterface.BUTTON_POSITIVE) {
                                Toast.makeText(applicationContext, "이용약관을 잘 읽어보시길 바랍니다.", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                Toast.makeText(applicationContext, "이용약관에 동의하여 주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    android.app.AlertDialog.Builder(this).run {
                        setTitle("이용 약관")
                        setIcon(android.R.drawable.ic_dialog_info)
                        setSingleChoiceItems(items, 0, object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, index: Int) {
                                Toast.makeText(
                                    this@MainActivity, "이용 약관을 잘 읽고 동의해 주시길 바랍니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                        setPositiveButton("닫기", eventHandler)
                        setCancelable(false)
                        show()
                    }
                        .setCanceledOnTouchOutside(true)
                    Toast.makeText(applicationContext,"이용약관을 이용해 주셔서 감사합니다.", Toast.LENGTH_SHORT).show()}
            }
            true
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(searchText: String?): Boolean {
                Log.d("chap12appbarproject","${searchText}")
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    //메뉴옵션을 클릭했을 때 이벤트 발생
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        when(item.itemId){
            R.id.menu_save ->{
                Toast.makeText(applicationContext,"회원정보변경", Toast.LENGTH_SHORT).show()}
            R.id.menu_search ->{Toast.makeText(applicationContext,"검색", Toast.LENGTH_SHORT).show()}
            R.id.menu_help ->{Toast.makeText(applicationContext,"도움말", Toast.LENGTH_SHORT).show()}
            R.id.menu_out ->{Toast.makeText(applicationContext,"회원탈퇴", Toast.LENGTH_SHORT).show()}
        }
        return super.onOptionsItemSelected(item)
    }
}