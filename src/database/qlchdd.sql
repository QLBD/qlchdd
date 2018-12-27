DROP DATABASE IF EXISTS qlchdd;

--
-- Database: `qlchdd`
--
CREATE SCHEMA IF NOT EXISTS `qlchdd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `qlchdd` ;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu_Nam` ()  NO SQL
SELECT Year(hoadonban.ngay_Ban) AS Nam, SUM(hoadonban.tongtien_Ban) AS TongDoanhThu
	FROM hoadonban
	Group by Nam$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu_Ngay` ()  NO SQL
SELECT Day(hoadonban.ngay_Ban) AS Ngay, SUM(hoadonban.tongtien_Ban) AS TongDoanhThu
	FROM hoadonban
	Group by Ngay$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu_Thang` ()  NO SQL
SELECT Month(hoadonban.ngay_Ban) AS Thang, SUM(hoadonban.tongtien_Ban) AS TongDoanhThu
	FROM hoadonban
	Group by Thang$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `HDBan_NgBan` (IN `NGBAN` DATE)  NO SQL
SELECT * FROM hoadonban WHERE hoadonban.ngay_Ban = NGBAN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `HDMua_NgNhap` (IN `NGNHAP` DATE)  NO SQL
SELECT * FROM hoadonmua WHERE hoadonmua.ngay_Nhap = NGNHAP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `KhachHang` (IN `TenKH` VARCHAR(45))  NO SQL
SELECT * FROM khachhang WHERE ten_KH LIKE CONCAT('%',TENKH,'%')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `NhanVien` (IN `TENNV` VARCHAR(45))  NO SQL
SELECT * FROM nhanvien WHERE ten_NV LIKE CONCAT('%',TENNV,'%')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SanPham` (IN `TENSP` VARCHAR(45))  NO SQL
SELECT * FROM sanpham WHERE ten_SP LIKE CONCAT('%',TENSP,'%')$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_NhanVien_DoanhThu` ()  NO SQL
SELECT nv.ma_NV, nv.ten_NV,Month(hdb.ngay_Ban) Thang, Year(hdb.ngay_Ban)Nam, SUM(hdb.tongtien_Ban) TongTienBan
	FROM nhanvien nv
    LEFT JOIN hoadonban hdb on hdb.ma_NV = nv.ma_NV
  
	GROUP BY nv.ma_NV, Thang, Nam
    ORDER BY nv.ma_NV$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongNam` (IN `nam` INT)  NO SQL
SELECT sp.ma_SP, sp.ten_SP, SUM(ct.sl) AS SL
FROM sanpham sp, cthd_ban ct, hoadonban hd
WHERE ct.sohd_Ban = hd.sohd_Ban and sp.ma_SP = ct.ma_SP and
	year(hd.ngay_Ban) = nam and sp.tinhtrang = 1
GROUP BY sp.ma_SP, sp.ten_SP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongQuy` (IN `quy` INT, IN `nam` INT)  NO SQL
SELECT sp.ma_SP , sp.ten_SP , SUM(ct.sl) AS SL
FROM sanpham sp, cthd_ban ct, hoadonban hd
WHERE ct.sohd_Ban = hd.sohd_Ban and sp.ma_SP = ct.ma_SP and
	year(hd.ngay_Ban) = nam and sp.tinhtrang = 1 and quarter(hd.ngay_Ban) = quy
GROUP BY sp.ma_SP, sp.ten_SP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongThang` (IN `thang` INT, IN `nam` INT)  NO SQL
SELECT sp.ma_SP , sp.ten_SP , SUM(ct.sl) AS SL
FROM sanpham sp, cthd_ban ct, hoadonban hd
WHERE ct.sohd_Ban = hd.sohd_Ban and sp.ma_SP = ct.ma_SP and
	year(hd.ngay_Ban) = nam and sp.tinhtrang = 1 and month(hd.ngay_Ban) = thang
GROUP BY sp.ma_SP, sp.ten_SP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_SLSP_BanTrongThang-Nam` (IN `th` INT(11), IN `n` INT(11))  NO SQL
SELECT sp.ma_SP, sp.ten_SP, sum(db.sl) sl
FROM sanpham sp LEFT JOIN (SELECT ct.ma_SP FROM cthd_ban ct, hoadonban hd WHERE ct.sohd_Ban = hd.sohd_Ban and Month(hd.ngay_Ban) = th and year(hd.ngay_Ban) = n) as db ON sp.ma_SP = db.ma_SP


GROUP BY sp.ma_SP$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `TK_TOP5SP_BanChay_Nam` ()  NO SQL
SELECT ct.ma_SP, sp.ten_SP, SUM(ct.sl) SLBanRa
FROM cthd_ban ct, sanpham sp
WHERE sp.ma_SP = ct.ma_SP

GROUP BY sp.ma_SP
ORDER BY SLBanRa DESC
LIMIT 5$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `USP_Login` (IN `tenDangNhap` VARCHAR(45), IN `matKhau` VARCHAR(45))  NO SQL
SELECT * FROM taikhoan WHERE taikhoan.ten_DangNhap = tenDangNhap AND taikhoan.matkhau_DangNhap = matKhau$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XuatHoaDon` (IN `sohd` INT)  NO SQL
SELECT sanpham.ten_SP, cthd_ban.sl, cthd_ban.gia_Goc , cthd_ban.tien_Giam, cthd_ban.thanhtien
FROM hoadonban, cthd_ban, sanpham
WHERE hoadonban.sohd_Ban = cthd_ban.sohd_Ban AND cthd_ban.ma_SP = sanpham.ma_SP AND hoadonban.sohd_Ban = sohd$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `baohanh`
--

CREATE TABLE `baohanh` (
  `ma_BH` int(11) NOT NULL,
  `sohd_Ban` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL,
  `serial` int(11) NOT NULL,
  `yeucau_BH` varchar(45) NOT NULL,
  `ngaynhan` datetime NOT NULL,
  `tinhtrang` int(11) NOT NULL,
  `ngaytra` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cthd_ban`
--

CREATE TABLE `cthd_ban` (
  `sohd_Ban` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL,
  `sl` int(11) DEFAULT NULL,
  `ma_KM` int(11) DEFAULT NULL,
  `gia_Goc` double DEFAULT NULL,
  `tien_Giam` double DEFAULT NULL,
  `thanhtien` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cthd_mua`
--

CREATE TABLE `cthd_mua` (
  `sohd_Mua` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL,
  `sl` int(11) DEFAULT NULL,
  `thanhtien` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ctkm`
--

CREATE TABLE `ctkm` (
  `ma_KM` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ctkm`
--

INSERT INTO `ctkm` (`ma_KM`, `ma_SP`) VALUES
(1, 3),
(4, 1),
(4, 2),
(4, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `doanhthu`
--

CREATE TABLE `doanhthu` (
  `thang` int(11) NOT NULL,
  `nam` int(11) NOT NULL,
  `tienban_SP` double NOT NULL,
  `tienmua_SP` double NOT NULL,
  `tienluong_NV` double NOT NULL,
  `tienloi` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hoadonban`
--

CREATE TABLE `hoadonban` (
  `sohd_Ban` int(11) NOT NULL,
  `ngay_Ban` date DEFAULT NULL,
  `ma_NV` int(11) DEFAULT NULL,
  `ma_KH` int(11) DEFAULT NULL,
  `tongtien_Ban` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `hoadonban`
--
DELIMITER $$
CREATE TRIGGER `insert_hoahong_tienhh` AFTER INSERT ON `hoadonban` FOR EACH ROW BEGIN
	DECLARE flag INT;
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienhh DOUBLE;
    
    SET nam = year(NEW.ngay_Ban), thang = month(NEW.ngay_Ban);
    
    
    SELECT COUNT(*) INTO flag FROM hoahong WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
    
    IF(flag = 0) THEN
    BEGIN
    	INSERT INTO `hoahong` (`ma_NV`, `thang`, `nam`, `tien_HH`) VALUES (NEW.ma_NV, thang, nam, NEW.tongtien_Ban); 
    END;
    ELSE
    BEGIN
    	SELECT hoahong.tien_HH INTO tienhh FROM hoahong WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
        
        SET tienhh = tienhh + NEW.tongtien_Ban;
        
        UPDATE hoahong SET hoahong.tien_HH = tienhh  WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
    END;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `hoadonmua`
--

CREATE TABLE `hoadonmua` (
  `sohd_Mua` int(11) NOT NULL,
  `ngay_Nhap` date DEFAULT NULL,
  `ma_NCC` int(11) DEFAULT NULL,
  `tongtien_Mua` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hoahong`
--

CREATE TABLE `hoahong` (
  `ma_NV` int(11) NOT NULL,
  `thang` int(2) NOT NULL,
  `nam` int(4) NOT NULL,
  `tien_HH` double(255,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `ma_KH` int(11) NOT NULL,
  `ten_KH` varchar(45) DEFAULT NULL,
  `soCMND_KH` int(11) DEFAULT NULL,
  `diachi_KH` varchar(45) DEFAULT NULL,
  `soDT_KH` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`ma_KH`, `ten_KH`, `soCMND_KH`, `diachi_KH`, `soDT_KH`, `email`) VALUES
(1, 'Nguyễn Văn Hậu', 321643275, 'Bình Thạnh', 348249328, NULL),
(2, 'Luis Alberto Suárez Díaz', 56789, 'Uruguay', 13456, NULL),
(3, 'Edson Arantes do Nascimento', 6789, 'Brazil', 5432, NULL),
(4, 'Vincent Willem van Gogh', 3444, 'Hà Lan', 333333, NULL),
(5, 'William Sydney Porter', 32156, 'Mỹ', 777887, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `ma_KM` int(11) NOT NULL,
  `ten_KM` varchar(45) DEFAULT NULL,
  `hs_KM` float DEFAULT NULL,
  `ngay_BD` date DEFAULT NULL,
  `ngay_KT` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`ma_KM`, `ten_KM`, `hs_KM`, `ngay_BD`, `ngay_KT`) VALUES
(1, 'Quốc Khánh', 0, '2018-09-02', '2018-09-02'),
(2, '30/4 - 1/5', 0.1, '2018-04-29', '2018-05-01'),
(3, '20/11/2018', 0.05, '2018-11-19', '2018-11-20'),
(4, 'Noel 2018', 0.07, '2018-12-23', '2018-12-25'),
(5, 'Tết dương lịch 2019', 0.15, '2018-12-29', '2019-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `ma_NCC` int(11) NOT NULL,
  `ten_NCC` varchar(45) DEFAULT NULL,
  `diachi_NCC` varchar(45) DEFAULT NULL,
  `soDT_NCC` int(11) DEFAULT NULL,
  `tinh_trang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`ma_NCC`, `ten_NCC`, `diachi_NCC`, `soDT_NCC`, `tinh_trang`) VALUES
(1, 'Alpha', 'Quận 2', 23456, 1),
(2, 'Higa', 'Gò Vấp', 19567, 1),
(3, 'LaLa', 'Quận 9', 666777, 1),
(4, 'Syb', 'Bình Chánh', 5656, 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `ma_NV` int(11) NOT NULL,
  `ten_NV` varchar(255) DEFAULT NULL,
  `soCMND_NV` int(9) DEFAULT NULL,
  `gioitinh` tinyint(1) DEFAULT NULL,
  `ngaysinh_NV` date DEFAULT NULL,
  `diachi_NV` varchar(45) DEFAULT NULL,
  `soDT_NV` int(11) DEFAULT NULL,
  `ngay_VaoLam` date DEFAULT NULL,
  `luong_CB` double DEFAULT NULL,
  `tinh_trang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`ma_NV`, `ten_NV`, `soCMND_NV`, `gioitinh`, `ngaysinh_NV`, `diachi_NV`, `soDT_NV`, `ngay_VaoLam`, `luong_CB`, `tinh_trang`) VALUES
(1, 'thang', 147258369, 1, '2018-12-06', 'sdfsadfsd', 926528212, '2018-12-07', 123, 1),
(2, 'Hồ Thái Thăng', 123456789, 1, '3898-02-01', 'ko có', 123456, '3918-11-01', 1000, 1),
(3, 'Hồ Thái Thăng', 123456789, 1, '3898-02-01', 'ko có', 123456, '3918-11-01', 1000, 1),
(4, 'Loui Pasteur', 345345, 1, '1822-12-27', 'Pháp', 123456, '2018-11-01', 2500000, 1),
(5, 'Thomas Alva Edison', 7878, 1, '1847-02-11', 'Mỹ', 567432, '2018-01-01', 3000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhasanxuat`
--

CREATE TABLE `nhasanxuat` (
  `ma_nsx` int(11) NOT NULL,
  `ten_nsx` varchar(45) NOT NULL,
  `thongtin` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhasanxuat`
--

INSERT INTO `nhasanxuat` (`ma_nsx`, `ten_nsx`, `thongtin`) VALUES
(1, 'ABC', ''),
(2, 'XYZ', ''),
(3, 'GHI', '');

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `ma_PhanQuyen` int(11) NOT NULL,
  `quyentruycap` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`ma_PhanQuyen`, `quyentruycap`) VALUES
(1, 'admin'),
(2, 'banhang');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `ma_SP` int(11) NOT NULL,
  `ten_SP` varchar(45) DEFAULT NULL,
  `ma_nsx` int(45) DEFAULT NULL,
  `sl` int(11) DEFAULT NULL,
  `nam_SX` int(4) DEFAULT NULL,
  `thue_VAT` double DEFAULT NULL,
  `gia_BanRa` double DEFAULT NULL,
  `thoigian_BH` int(11) DEFAULT NULL,
  `xuatxu` varchar(45) DEFAULT NULL,
  `mau` varchar(45) DEFAULT NULL,
  `bonho` varchar(45) DEFAULT NULL,
  `kichthuoc` varchar(45) DEFAULT NULL,
  `anh` longblob,
  `tinhtrang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`ma_SP`, `ten_SP`, `ma_nsx`, `sl`, `nam_SX`, `thue_VAT`, `gia_BanRa`, `thoigian_BH`, `xuatxu`, `mau`, `bonho`, `kichthuoc`, `anh`, `tinhtrang`) VALUES
(1, 'iPhone 6 32GB', 1, 7, 2017, 0.1, 7000000, 12, 'Trung Quốc', 'Hồng', '32G', NULL, NULL, 1),
(2, 'Samsung Galaxy A9', 3, 10, 2018, 0.2, 12500000, 12, NULL, 'Đen', '128GB', '6.3 inch Full HD+ (1080 x 2220 Pixels)', NULL, 1),
(3, 'Samsung Galaxy S8', 2, 8, 2018, 0.2, 16000000, 12, NULL, 'Xanh', '64GB', '2K+ (1440 x 2960 Pixels)', NULL, 1),
(4, 'Oppo neo 9', 1, 5, 2017, 0.03, 5000000, 12, NULL, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `ten_DangNhap` varchar(45) NOT NULL,
  `matkhau_DangNhap` varchar(45) DEFAULT NULL,
  `ma_PhanQuyen` int(11) DEFAULT NULL,
  `ma_NV` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`ten_DangNhap`, `matkhau_DangNhap`, `ma_PhanQuyen`, `ma_NV`) VALUES
('admin', 'C31F804A0E4A8943A7A5577A292F2321', 1, NULL),
('nhanvien', '9B84756F9A50CC0D8223B9A03842CAC4', 2, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `baohanh`
--
ALTER TABLE `baohanh`
  ADD PRIMARY KEY (`ma_BH`),
  ADD KEY `sohd_Ban` (`sohd_Ban`),
  ADD KEY `ma_SP` (`ma_SP`);

--
-- Indexes for table `cthd_ban`
--
ALTER TABLE `cthd_ban`
  ADD PRIMARY KEY (`sohd_Ban`,`ma_SP`),
  ADD KEY `fk_CTHDB_KM` (`ma_KM`),
  ADD KEY `fk_CTHDB_SP` (`ma_SP`);

--
-- Indexes for table `cthd_mua`
--
ALTER TABLE `cthd_mua`
  ADD PRIMARY KEY (`sohd_Mua`,`ma_SP`),
  ADD KEY `fk_CTHDM_SP` (`ma_SP`);

--
-- Indexes for table `ctkm`
--
ALTER TABLE `ctkm`
  ADD PRIMARY KEY (`ma_KM`,`ma_SP`),
  ADD KEY `fk_CTKM_SP` (`ma_SP`);

--
-- Indexes for table `doanhthu`
--
ALTER TABLE `doanhthu`
  ADD PRIMARY KEY (`thang`,`nam`);

--
-- Indexes for table `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD PRIMARY KEY (`sohd_Ban`),
  ADD KEY `fk_hd_kh` (`ma_KH`),
  ADD KEY `fk_hd_nv` (`ma_NV`);

--
-- Indexes for table `hoadonmua`
--
ALTER TABLE `hoadonmua`
  ADD PRIMARY KEY (`sohd_Mua`),
  ADD KEY `fk_HDM_NCC` (`ma_NCC`);

--
-- Indexes for table `hoahong`
--
ALTER TABLE `hoahong`
  ADD PRIMARY KEY (`ma_NV`,`thang`,`nam`),
  ADD KEY `ma_NV` (`ma_NV`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`ma_KH`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`ma_KM`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`ma_NCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`ma_NV`);

--
-- Indexes for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  ADD PRIMARY KEY (`ma_nsx`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`ma_PhanQuyen`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`ma_SP`),
  ADD KEY `ma_nsx` (`ma_nsx`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`ten_DangNhap`),
  ADD KEY `fk_TK_NV` (`ma_NV`),
  ADD KEY `fk_TK_PQ` (`ma_PhanQuyen`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `baohanh`
--
ALTER TABLE `baohanh`
  MODIFY `ma_BH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ctkm`
--
ALTER TABLE `ctkm`
  MODIFY `ma_KM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hoadonban`
--
ALTER TABLE `hoadonban`
  MODIFY `sohd_Ban` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `hoadonmua`
--
ALTER TABLE `hoadonmua`
  MODIFY `sohd_Mua` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `ma_KH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  MODIFY `ma_KM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `ma_NCC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `ma_NV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  MODIFY `ma_nsx` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `ma_PhanQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `ma_SP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `baohanh`
--
ALTER TABLE `baohanh`
  ADD CONSTRAINT `baohanh_ibfk_1` FOREIGN KEY (`sohd_Ban`) REFERENCES `hoadonban` (`sohd_Ban`),
  ADD CONSTRAINT `baohanh_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`);

--
-- Constraints for table `cthd_ban`
--
ALTER TABLE `cthd_ban`
  ADD CONSTRAINT `cthd_ban_ibfk_1` FOREIGN KEY (`sohd_Ban`) REFERENCES `hoadonban` (`sohd_Ban`),
  ADD CONSTRAINT `cthd_ban_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`),
  ADD CONSTRAINT `cthd_ban_ibfk_3` FOREIGN KEY (`ma_KM`) REFERENCES `khuyenmai` (`ma_KM`);

--
-- Constraints for table `cthd_mua`
--
ALTER TABLE `cthd_mua`
  ADD CONSTRAINT `cthd_mua_ibfk_1` FOREIGN KEY (`sohd_Mua`) REFERENCES `hoadonmua` (`sohd_Mua`),
  ADD CONSTRAINT `cthd_mua_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`);

--
-- Constraints for table `ctkm`
--
ALTER TABLE `ctkm`
  ADD CONSTRAINT `ctkm_ibfk_1` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`),
  ADD CONSTRAINT `ctkm_ibfk_2` FOREIGN KEY (`ma_KM`) REFERENCES `khuyenmai` (`ma_KM`);

--
-- Constraints for table `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD CONSTRAINT `hoadonban_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`),
  ADD CONSTRAINT `hoadonban_ibfk_2` FOREIGN KEY (`ma_KH`) REFERENCES `khachhang` (`ma_KH`);

--
-- Constraints for table `hoadonmua`
--
ALTER TABLE `hoadonmua`
  ADD CONSTRAINT `hoadonmua_ibfk_1` FOREIGN KEY (`ma_NCC`) REFERENCES `nhacungcap` (`ma_NCC`);

--
-- Constraints for table `hoahong`
--
ALTER TABLE `hoahong`
  ADD CONSTRAINT `hoahong_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`ma_nsx`) REFERENCES `nhasanxuat` (`ma_nsx`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`),
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`ma_PhanQuyen`) REFERENCES `phanquyen` (`ma_PhanQuyen`);
COMMIT;