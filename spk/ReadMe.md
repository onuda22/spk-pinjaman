Berdasarkan hasil diskusi kita, berikut adalah detail tabel yang diperlukan untuk mini-project sistem pendukung keputusan pinjaman:

### **1. Tabel Nasabah**

| **Column Name** | **Data Type** | **Description**                           |
|-----------------|---------------|-------------------------------------------|
| `customer_id`   | INT           | ID unik untuk nasabah                     |
| `name`          | VARCHAR       | Nama nasabah                              |
| `age`           | INT           | Usia nasabah                              |
| `income`        | DECIMAL       | Pendapatan bulanan nasabah                |
| `credit_score`  | INT           | Skor kredit nasabah                       |
| `character_rating` | ENUM('Sangat Buruk', 'Buruk', 'Cukup', 'Baik', 'Sangat Baik') | Penilaian karakter nasabah                 |

### **2. Tabel Pinjaman**

| **Column Name** | **Data Type** | **Description**                           |
|-----------------|---------------|-------------------------------------------|
| `loan_id`       | INT           | ID unik untuk pinjaman                    |
| `customer_id`   | INT           | ID nasabah yang mengajukan pinjaman       |
| `loan_amount`   | DECIMAL       | Jumlah pinjaman                           |
| `loan_term`     | INT           | Lama pinjaman (dalam bulan)               |
| `collateral_type` | VARCHAR      | Jenis jaminan yang diberikan              |
| `collateral_value` | DECIMAL     | Nilai jaminan yang diberikan              |

### **3. Tabel Analisis**

| **Column Name** | **Data Type** | **Description**                           |
|-----------------|---------------|-------------------------------------------|
| `loan_id`       | INT           | ID unik untuk pinjaman                    |
| `loan_amount`   | DECIMAL       | Jumlah pinjaman                           |
| `income`        | DECIMAL       | Pendapatan bulanan nasabah                |
| `credit_score`  | INT           | Skor kredit nasabah                       |
| `age`           | INT           | Usia nasabah                              |
| `loan_term`     | INT           | Lama pinjaman (dalam bulan)               |
| `loan_to_income_ratio` | DECIMAL   | Rasio pinjaman terhadap pendapatan        |
| `collateral_value` | DECIMAL     | Nilai jaminan                             |
| `character_rating` | ENUM('Sangat Buruk', 'Buruk', 'Cukup', 'Baik', 'Sangat Baik') | Penilaian karakter nasabah                 |
| `eligibility_score` | DECIMAL     | Skor kelayakan pinjaman                   |
| `risk_level`    | ENUM('Low Risk', 'Medium Risk', 'High Risk') | Tingkat risiko pinjaman                   |

### **4. Tabel Pengaturan Bobot**

| **Column Name**      | **Data Type** | **Description**                           |
|----------------------|---------------|-------------------------------------------|
| `variable_name`      | VARCHAR       | Nama variabel (misalnya 'Income', 'Credit Score') |
| `weight`             | DECIMAL       | Bobot variabel tersebut dalam analisis    |

### **Contoh Tabel Pengaturan Bobot**

| **variable_name**  | **weight** |
|--------------------|------------|
| `income`           | 20         |
| `credit_score`     | 20         |
| `age`              | 10         |
| `loan_term`        | 10         |
| `loan_to_income_ratio` | 15    |
| `collateral_value` | 15         |
| `character_rating` | 20         |

### **Relasi Antar Tabel**

1. **Tabel Nasabah** berhubungan dengan **Tabel Pinjaman** melalui `customer_id`.
2. **Tabel Pinjaman** berhubungan dengan **Tabel Analisis** melalui `loan_id`.

### **Penggunaan Data**

- **Tabel Nasabah**: Menyimpan informasi dasar tentang nasabah.
- **Tabel Pinjaman**: Menyimpan informasi tentang pinjaman yang diajukan oleh nasabah.
- **Tabel Analisis**: Menggabungkan data dari tabel nasabah dan pinjaman untuk menghitung skor kelayakan dan menentukan risiko.
- **Tabel Pengaturan Bobot**: Menyimpan bobot untuk setiap variabel penilaian yang digunakan dalam perhitungan skor kelayakan.

Dengan tabel-tabel ini, Anda dapat melakukan analisis dan menghitung kelayakan serta risiko pinjaman dengan menggunakan data yang telah disediakan.