# Keuanganku

adalah aplikasi pencatatan pemasukkan dan pengeluaran berbasis android mobile
menggunakan Android MVP Architecture dan juga ROOM untuk penyimpanan lokal


## MVP Architecture

Ketika kami mengikuti pola arsitektur seperti MVP, MVVM, MVP clean, 
kami selalu menemukan tugas kecil namun berulang-ulang untuk membuat 
file dasar seperti Android Activity, Presenter, View, Api model dan 
kemudian menulis kode pelat boiler. Ini biasanya memakan waktu 1-2 jam 
untuk setiap layar. Untuk mempermudah pekerjaan dan menghemat waktu, 
Kami telah membuat templat otomatis yang akan berfungsi di atas dalam 
waktu kurang dari 20 detik.

sumber : [github.com/MindorksOpenSource/android-mvp-architecture](https://github.com/MindorksOpenSource/android-mvp-architecture)




## Android ROOM

Room adalah perpustakaan kegigihan baru Google yang dirancang untuk
membuatnya lebih mudah untuk membangun aplikasi offline. 
Ia mencoba untuk mengekspos API yang dapat memanfaatkan 
kekuatan penuh SQL sambil tetap menyediakan lapisan abstraksi 
untuk mengelola data sebagai objek Java. Ini juga berfungsi dengan baik 
dengan perpustakaan Komponen Arsitektur Google untuk membangun aplikasi 
produksi berkualitas tinggi yang kuat dan juga dapat digunakan bersama 
dengan Paging Library untuk menangani kumpulan data besar.


sumber : [github.com/codepath/android_guides/wiki/Room-Guide](https://github.com/codepath/android_guides/wiki/Room-Guide)




# Overview



## Splash Activity

adalah activity yang akan ditampilkan saat aplikasi di buka,
activity ini juga akan menjalankan service yang nantinya akan
melakukan monitor untuk transaksi yang expired

![GitHub Logo](/design/app_1.png) 




## Main Menu Activity

adalah activity yang akan menampilkan total saldo dan menu pengeluaran serta
satu menu laporan, dimana pada saat saldo di klik oleh user, maka akan masuk
ke form input pemasukkan, terdapat 3 menu pengeluaran dan satu menu laporan
menu-menu tersebut menggunakan recycleview dengan 4 item, jika salah satu item di klik
dan di validasi id menunya, maka akan menjalankan aktivity yg dituju sesuai dengan
id menu, contoh saat user menklik menu pengeluaran, maka user akan diarakan ke form
input pengeluaran

![GitHub Logo](/design/app_2.png) 




## Expense Activity

ini adalah activity yang digunakan untuk menginputkan transaksi pengeluaran
pada saat user menginputkan data-data, maka data-data tersebut akan di insert
ke tabel yang berada di database lokal yg menggunakan android ROOM

![GitHub Logo](/design/app_3.png) 




## Income Activity

ini adalah activity yang digunakan untuk menginputkan transaksi pemasukan
pada saat user menginputkan data-data, maka data-data tersebut akan di insert
ke tabel yang berada di database lokal yg menggunakan android ROOM,
berbeda dengan pengeluaran, activity ini juga akan menampilkan data-data pemasukkan
yg berbentuk list laporan, yg berada tepat dibawah form

![GitHub Logo](/design/app_4.png) 




## Report Menu Activity

ini adalah aktivity yang menampilkan 4 menu laporan yang ditampilkan menggunakan
recycleview, sama seperti pada menu utama, pada saat salah satu item diclik
maka akan divalidasi,lalu user akan diarahkan ke activity report diagram
yg akan menampilkan konten laporan sesuai dengan menu yg di klik oleh user

![GitHub Logo](/design/app_5.png) 




## Report Diagram Activity (List Report)

adalah salah satu konten dalam activity report diagram yg hanya akan ditampilkan ke user
apabila user memilih menu list report, report ini akan menujukan data laporan pemasukan dan
pengeluaran dalam bentuk tabel yg disusun dan diurutkan berdasarkan tanggal

![GitHub Logo](/design/app_6.png) 



## Report Diagram Activity (Line Report)

adalah salah satu konten dalam activity report diagram yg hanya akan ditampilkan ke user
apabila user memilih menu Line Report, report ini akan menujukan data laporan pemasukan dan
pengeluaran dalam bentuk line diagram yg disusun dan diurutkan berdasarkan tanggal

![GitHub Logo](/design/app_7.png) 




## Report Diagram Activity (Pie Chart Report)

adalah salah satu konten dalam activity report diagram yg hanya akan ditampilkan ke user
apabila user memilih menu Pie Chart Report, report ini akan menujukan total saldo pemasukan dan
pengeluaran dalam bentuk pie diagram untuk menujukan presentase besarnya pemasukan dan pengeluaran


![GitHub Logo](/design/app_8.png) 




## Report Diagram Activity (Waterfall Chart Report)

adalah salah satu konten dalam activity report diagram yg hanya akan ditampilkan ke user
apabila user memilih menu Waterfall Chart Report, report ini akan menujukan data naik turunnya saldo
dalam bentuk Waterfall diagram



![GitHub Logo](/design/app_9.png) 




## Setting Activity

adalah activity yang akan menentukan kapan user mau menerima notifikasi
pada saat ada transaksi yg expired terdeteksi


![GitHub Logo](/design/app_10.png) 




## Notification

adalah tampilan notifikasi bar untuk transaksi expired, saldo yang minus
dan selamat datang, untuk notif selamat datang untuk saat ini belum di implementasi

![GitHub Logo](/design/app_11.png) 



## Dialog Delete Transaction

adalah tampilan alert dialog yg akan menanyakan kepada user
apakah ia yakin ingin menghapus data transaksi yg dipilih

![GitHub Logo](/design/app_12.png) 