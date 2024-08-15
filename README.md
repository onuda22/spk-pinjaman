# Aplikasi Sederhana Sistem Pendukung Keputusan

### Sistem Pendukung Keputusan Pemberian Pinjaman

Aplikasi ini adalah sistem pendukung keputusan untuk membantu bank dalam menilai kelayakan pinjaman nasabah. Menggunakan Java Spring Boot dan Native Query, aplikasi ini menghitung skor kelayakan pinjaman berdasarkan variabel yang telah ditentukan dan memberikan penilaian risiko kepada pinjaman yang diajukan.

## Fitur

- Menghitung Skor Kelayakan Pinjaman: Berdasarkan variabel seperti skor kredit, pendapatan, jumlah pinjaman, nilai jaminan, dan rasio utang terhadap pendapatan.
- Menentukan Tingkat Risiko: Mengkategorikan risiko pinjaman sebagai Low Risk, Medium Risk, atau High Risk.
- CRUD Operasi untuk Data Pinjaman: Insert, update, dan delete data pinjaman dan nasabah.
- API untuk Analisis Pinjaman: Endpoint untuk menganalisis dan mendapatkan hasil kelayakan pinjaman.

## Teknologi yang Digunakan

- Java Spring Boot: Framework untuk membangun aplikasi backend.
- Native Query: Digunakan untuk menjalankan query SQL langsung.
- MySQL/PostgreSQL: Database relasional untuk menyimpan data pinjaman dan nasabah.

## Requirement

- Java 11 atau lebih tinggi
- Maven
- MySQL/PostgreSQL
- Spring Boot 2.5.3 atau lebih tinggi
- Spring Data JPA
- Spring Web

Aplikasi dibangun menggunakan database Postgre namun dapat disesuaikan pada file [properties](spk/src/main/resources/application.properties) saat akan menjalankan aplikasi.

### Dokumentasi

Dokumentasi API (postman): [Endpoint](https://elements.getpostman.com/redirect?entityId=23634596-6b624796-cf45-420e-ad07-5c4ceb61947b&entityType=collection)
