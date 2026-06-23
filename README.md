# Ứng dụng Thi Trắc Nghiệm Trên Android

## 1. Giới thiệu đề tài

### Bài toán

Hiện nay nhu cầu học tập và kiểm tra kiến thức trên thiết bị di động ngày càng phổ biến. Việc xây dựng một ứng dụng thi trắc nghiệm giúp người học dễ dàng ôn tập, làm bài kiểm tra và theo dõi kết quả.

### Mục tiêu

- Xây dựng ứng dụng thi trắc nghiệm trên Android.
- Quản lý đề thi và câu hỏi.
- Chấm điểm tự động.
- Lưu lịch sử thi.
- Thống kê dữ liệu hệ thống.

---

## 2. Dataset / Dữ liệu sử dụng

Hệ thống sử dụng dữ liệu được lưu bằng SQLite.

Các bảng chính:

### TaiKhoan

* MaTK
* TenDangNhap
* MatKhau
* HoTen
* VaiTro

### DeThi

* MaDe
* TenDe
* MoTa
* ThoiGian

### CauHoi

* MaCH
* MaDe
* NoiDung
* DapAnA
* DapAnB
* DapAnC
* DapAnD
* DapAnDung

### BaiThi

* MaBaiThi
* MaTK
* MaDe
* NgayThi
* SoCauDung
* Diem

### ChiTietBaiThi

* MaChiTiet
* MaBaiThi
* MaCH
* DapAnChon

### KetQua

* MaKetQua
* MaBaiThi
* TongSoCau
* SoCauDung
* SoCauSai
* Diem

---

## 3. Quy trình hoạt động

Đăng ký
↓
Đăng nhập
↓
Chọn đề thi
↓
Làm bài thi
↓
Nộp bài
↓
Chấm điểm tự động
↓
Lưu kết quả
↓
Xem lịch sử thi

---

## 4. Công nghệ sử dụng

- Java
- Android Studio
- SQLite
- Material Design
- UML (Use Case, Activity Diagram, Class Diagram)

---

## 5. Kết quả đạt được

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
- Quản lý câu hỏi
- Thống kê hệ thống
- Đăng xuất

---

## 6. Hướng dẫn chạy chương trình

### Cài đặt môi trường

- Android Studio Hedgehog hoặc mới hơn
- JDK 17
- Android SDK API 35+

### Clone project

git clone <link_github>

### Mở project

- Mở Android Studio
- Chọn Open
- Chọn thư mục Quiz-App

### Chạy ứng dụng

- Tạo Android Emulator
  hoặc
- Kết nối điện thoại Android

Nhấn Run để chạy ứng dụng.

---

## 7. Cấu trúc thư mục

app/ : Source code Android

demo/ : Hình ảnh và video demo

data/ : Tài liệu dữ liệu mẫu

reports/ : Báo cáo đồ án

slides/ : Slide thuyết trình

README.md : Hướng dẫn sử dụng

---

## 8. Tác giả

Họ tên: Cao Duy Mạnh

MSSV: 12523099

Lớp: 12523T.1

Trường: Đại học sư phạm kỹ thuật Hưng Yên
