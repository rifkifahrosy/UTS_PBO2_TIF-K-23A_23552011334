# UTS Pemrograman Berorientasi Obyek 2
<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 2</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>

## Profil
<ul>
  <li>Nama: Muhammad Rifki Fahrosy</li>
  <li>NIM: 23552011334</li>
  <li>Studi Kasus: Sistem Todo List Fullstack (Spring Boot + Thymeleaf)</li>
</ul>

## Judul Studi Kasus
<p>Sistem Todo List Fullstack (Spring Boot + Thymeleaf).</p>

## Penjelasan Studi Kasus 
### 📝 Sistem Todo List Fullstack (Spring Boot + Thymeleaf)
Sebuah aplikasi manajemen tugas (todo list) modern berbasis Java, dibangun menggunakan **Spring Boot**, **Thymeleaf**, dan **Spring Security**. Mendukung autentikasi pengguna, CRUD tugas, subtask, deadline, dan tampilan modern dengan Bootstrap 5.

## 🚀 Fitur Utama

### 🔐 Autentikasi (Login & Register)
- Sistem login berbasis **session**
- Pengguna hanya bisa melihat tugas miliknya
- Proteksi halaman menggunakan **Spring Security**

### ✅ Manajemen Todo
- Tambah, lihat, edit, hapus tugas
- Tandai tugas sebagai **selesai / belum selesai**
- Filter tampilan: **Semua / Selesai / Belum Selesai**
- Tampilkan **tanggal deadline**

### 🧩 Subtask
- Tambahkan subtask ke dalam tugas
- Tandai subtask selesai
- Hapus subtask
- Subtask ditampilkan dalam tampilan collapsible

### 🎨 UI Modern
- Menggunakan **Bootstrap 5**
- Tampilan bersih dan responsif
- Modal konfirmasi logout
- Alert notifikasi berhasil / gagal

---

## 🏗️ Teknologi yang Digunakan

| Teknologi | Deskripsi |
|-----------|-----------|
| Java 17+ | Bahasa utama |
| Spring Boot | Framework backend |
| Thymeleaf | Template engine untuk HTML |
| Spring Security | Autentikasi dan otorisasi |
| Bootstrap 5 | UI modern dan responsif |
| H2 / MySQL | Basis data (bisa pilih) |
| JPA & Hibernate | ORM dan query database |

---

## 📂 Struktur Project

```
src/
├── controller/       --> Menangani routing & request
├── model/            --> Entity JPA: User, ToDo, SubTask
├── repository/       --> Interface JPA untuk akses DB
├── service/          --> Logika bisnis & autentikasi
├── templates/        --> View Thymeleaf (HTML)
└── config/           --> Konfigurasi keamanan Spring Security
```
## 🛠️ Cara Menjalankan

1. Buka di IDE (IntelliJ / VSCode)
2. Jalankan `DemoApplication.java`
3. Akses aplikasi di:
   ```
   http://localhost:8080/login
   ```

---


## 🧠 Penjelasan: 4 Pilar OOP (Object-Oriented Programming)

| Pilar | Penjelasan | Contoh di Project |
|-------|------------|-------------------|
| **1. Encapsulation (Enkapsulasi)** | Menyembunyikan detail internal dan hanya mengekspos bagian penting melalui getter/setter. | Field di `ToDo`, `User`, dan `SubTask` bersifat private, diakses melalui getter/setter. |
| **2. Inheritance (Pewarisan)** | Satu class dapat mewarisi atribut dan metode dari class lain. | Kalau kamu buat class service yang extend class base service, itu contoh pewarisan. (Belum langsung digunakan di proyek ini tapi konsepnya familiar dalam Spring melalui pewarisan framework class) |
| **3. Polymorphism (Polimorfisme)** | Satu fungsi/objek bisa memiliki banyak bentuk tergantung konteksnya. | Contohnya: Override method `loadUserByUsername()` di `CustomUserDetailsService` untuk login. |
| **4. Abstraction (Abstraksi)** | Menyembunyikan kompleksitas dan menampilkan antarmuka yang sederhana. | Interface `UserService`, `ToDoRepository`, dan `SubTaskRepository` hanya mendeklarasikan kontrak — implementasinya disembunyikan. |

## Demo Proyek
<ul>
  <li>Github: <a href="">https://github.com/rifkifahrosy/UTS_PBO2_TIF-K-23A_23552011334.git</a></li>
  <li>GDrive: <a href="">https://drive.google.com/drive/folders/1x088IMgL2PndzDSnr4SFrKrnG5oPciIt?usp=drive_link</a></li>
</ul>
