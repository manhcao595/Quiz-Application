# Ứng dụng Thi Trắc Nghiệm Trên Android

## 1. Giới thiệu đề tài

### Bài toán

Hiện nay nhu cầu học tập và kiểm tra kiến thức trên thiết bị di động ngày càng phổ biến. Việc xây dựng một ứng dụng thi trắc nghiệm giúp người học dễ dàng ôn tập, kiểm tra kiến thức và theo dõi kết quả học tập mọi lúc mọi nơi.

### Mục tiêu

- Xây dựng ứng dụng thi trắc nghiệm trên nền tảng Android.
- Hỗ trợ đăng ký và đăng nhập tài khoản.
- Quản lý đề thi và câu hỏi.
- Tự động chấm điểm bài thi.
- Lưu lịch sử làm bài và kết quả thi.
- Thống kê dữ liệu hệ thống dành cho quản trị viên.

---

## 2. Dataset / Dữ liệu sử dụng

### Nguồn dữ liệu

Dữ liệu được xây dựng thủ công và lưu trữ cục bộ bằng SQLite trong ứng dụng Android.

Cơ sở dữ liệu được khởi tạo thông qua lớp `DatabaseHelper`.

### Các bảng dữ liệu chính

#### TaiKhoan

- MaTK
- TenDangNhap
- MatKhau
- HoTen
- VaiTro

#### DeThi

- MaDe
- TenDe
- MoTa
- ThoiGian

#### CauHoi

- MaCH
- MaDe
- NoiDung
- DapAnA
- DapAnB
- DapAnC
- DapAnD
- DapAnDung

#### BaiThi

- MaBaiThi
- MaTK
- MaDe
- NgayThi
- SoCauDung
- Diem

#### ChiTietBaiThi

- MaChiTiet
- MaBaiThi
- MaCH
- DapAnChon

#### KetQua

- MaKetQua
- MaBaiThi
- TongSoCau
- SoCauDung
- SoCauSai
- Diem

---

## 3. Pipeline xử lý hệ thống

Đăng ký tài khoản

↓

Đăng nhập

↓

Chọn đề thi

↓

Tải câu hỏi từ SQLite

↓

Làm bài thi

↓

Nộp bài

↓

Chấm điểm tự động

↓

Lưu kết quả

↓

Xem kết quả thi

↓

Xem lịch sử thi

---

## 4. Kiến trúc hệ thống

Ứng dụng được xây dựng theo kiến trúc:

Activity

↓

DAO

↓

SQLite

Trong đó:

- Activity: Quản lý giao diện và tương tác với người dùng.
- DAO (Data Access Object): Thực hiện các thao tác truy xuất dữ liệu.
- SQLite: Lưu trữ dữ liệu cục bộ của hệ thống.

Ví dụ:

LoginActivity

↓

TaiKhoanDAO

↓

SQLite

---

## 5. Công nghệ sử dụng

- Java
- Android Studio
- SQLite
- Material Design
- UML (Use Case Diagram, Sequence Diagram, Class Diagram)

---

## 6. Kết quả đạt được

### Chức năng người dùng

- Đăng ký tài khoản
- Đăng nhập
- Xem danh sách đề thi
- Làm bài thi
- Xem kết quả thi
- Xem chi tiết bài làm
- Xem lịch sử thi
- Đăng xuất

### Chức năng quản trị

- Quản lý đề thi
- Thêm đề thi
- Sửa đề thi
- Xóa đề thi
- Quản lý câu hỏi
- Thêm câu hỏi
- Sửa câu hỏi
- Xóa câu hỏi
- Thống kê hệ thống
- Đăng xuất

### Thống kê hệ thống

- Tổng số tài khoản
- Tổng số đề thi
- Tổng số câu hỏi
- Tổng số lượt thi

### Kết quả triển khai

- Hoàn thiện 2 phân hệ User và Admin.
- Hoàn thiện cơ sở dữ liệu SQLite gồm 6 bảng.
- Triển khai thành công trên Android Emulator.
- Có thể chạy trên thiết bị Android thực tế.

---

## 7. Hướng dẫn chạy chương trình

### Yêu cầu môi trường

- Android Studio Hedgehog hoặc mới hơn
- JDK 17
- Android SDK API 35+

### Clone project

```bash
git clone https://github.com/<username>/Quiz-Application.git
```

### Mở project

- Mở Android Studio
- Chọn Open Project
- Chọn thư mục Quiz-Application

### Chạy ứng dụng

- Tạo Android Emulator

hoặc

- Kết nối điện thoại Android thật

Sau đó nhấn Run để khởi động ứng dụng.

---

## 8. Tài khoản mặc định

### Quản trị viên

Tên đăng nhập:

```text
admin
```

Mật khẩu:

```text
123456
```

---

## 9. Cấu trúc thư mục dự án

```text
Quiz-Application
│
├── app/    Source code Android
│
├── demo/
│   ├── screenshots
│   └── demo_video.mp4
│
├── data/
│   ├── database_schema.sql
│   └── sample_data.txt
│
├── reports/
│   ├── BaoCao.docx
│   └── BaoCao.pdf
│
├── slides/
│   ├── Slide.pptx
│   └── Slide.pdf
│
├── README.md
├── requirements.txt
└── .gitignore
```

---

## 10. Tác giả

Họ tên: Cao Duy Mạnh

MSSV: 12523099

Lớp: 12523T.1

Trường: Đại học Sư phạm Kỹ thuật Hưng Yên
