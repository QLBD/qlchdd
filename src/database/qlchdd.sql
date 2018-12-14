/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  THAITHANG
 * Created: Dec 14, 2018
 */

--
-- Database: `qlchdd`
--


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

-- --------------------------------------------------------

--
-- Table structure for table `hoadonban`
--

CREATE TABLE `hoadonban` (
  `sohd_Ban` int(11) NOT NULL,
  `ngay_Ban` date DEFAULT NULL,
  `ma_NV` int(11) DEFAULT NULL,
  `ma_KH` int(11) DEFAULT NULL,
  `tongtien_Ban` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hoadonmua`
--

CREATE TABLE `hoadonmua` (
  `sohd_Mua` int(11) NOT NULL,
  `ngay_Nhap` date DEFAULT NULL,
  `ma_NCC` int(11) DEFAULT NULL,
  `tongtien_Mua` double DEFAULT NULL
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
-- Table structure for table `nhasanxuat`
--

CREATE TABLE `nhasanxuat` (
  `ma_nsx` int(11) NOT NULL,
  `ten_nsx` varchar(45) NOT NULL,
  `thongtin` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `ma_PhanQuyen` int(11) NOT NULL,
  `quyentruycap` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
  `tinhtrang` int(11) DEFAULT NULL,
  `heso_HH` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`ma_NV`, `ten_NV`, `soCMND_NV`, `gioitinh`, `ngaysinh_NV`, `diachi_NV`, `soDT_NV`, `ngay_VaoLam`, `luong_CB`, `tinh_trang`) VALUES
(1, 'thang', 147258369, 1, '2018-12-06', 'sdfsadfsd', 926528212, '2018-12-07', 123, 1),
(2, 'Hồ Thái Thăng', 123456789, 1, '3898-02-01', 'ko có', 123456, '3918-11-01', 1000, 1),
(3, 'Hồ Thái Thăng', 123456789, 1, '3898-02-01', 'ko có', 123456, '3918-11-01', 1000, 1);

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`ma_PhanQuyen`, `quyentruycap`) VALUES
(1, 'admin'),
(2, 'banhang');

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
-- ALTER TABLE `nhanvien`
--   ADD PRIMARY KEY (`ma_NV`);
-- ALTER TABLE `nhanvien` ADD FULLTEXT KEY `ten_NV` (`ten_NV`);

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
-- Constraints for dumped tables
--

--
-- Constraints for table `baohanh`
--
ALTER TABLE `baohanh`
  ADD CONSTRAINT `FK_t7bbmrq3qvuhygcy6wqq3robr` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`),
  ADD CONSTRAINT `baohanh_ibfk_2` FOREIGN KEY (`sohd_Ban`) REFERENCES `hoadonban` (`sohd_Ban`);

--
-- Constraints for table `cthd_ban`
--
ALTER TABLE `cthd_ban`
  ADD CONSTRAINT `fk_CTHDB_HDB` FOREIGN KEY (`sohd_Ban`) REFERENCES `hoadonban` (`sohd_Ban`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_CTHDB_KM` FOREIGN KEY (`ma_KM`) REFERENCES `khuyenmai` (`ma_KM`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_CTHDB_SP` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`) ON UPDATE CASCADE;

--
-- Constraints for table `cthd_mua`
--
ALTER TABLE `cthd_mua`
  ADD CONSTRAINT `fk_CTHDM_HDM` FOREIGN KEY (`sohd_Mua`) REFERENCES `hoadonmua` (`sohd_Mua`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_CTHDM_SP` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`) ON UPDATE CASCADE;

--
-- Constraints for table `ctkm`
--
ALTER TABLE `ctkm`
  ADD CONSTRAINT `fk_CTKM_KM` FOREIGN KEY (`ma_KM`) REFERENCES `khuyenmai` (`ma_KM`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_CTKM_SP` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`) ON UPDATE CASCADE;

--
-- Constraints for table `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD CONSTRAINT `fk_hd_kh` FOREIGN KEY (`ma_KH`) REFERENCES `khachhang` (`ma_KH`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_hd_nv` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`) ON UPDATE CASCADE;

--
-- Constraints for table `hoadonmua`
--
ALTER TABLE `hoadonmua`
  ADD CONSTRAINT `fk_HDM_NCC` FOREIGN KEY (`ma_NCC`) REFERENCES `nhacungcap` (`ma_NCC`) ON UPDATE CASCADE;

--
-- Constraints for table `hoahong`
--
ALTER TABLE `hoahong`
  ADD CONSTRAINT `hoahong_ibfk_1` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`);

--
-- Constraints for table `nhasanxuat`
--
ALTER TABLE `nhasanxuat`
  ADD CONSTRAINT `nhasanxuat_ibfk_1` FOREIGN KEY (`ma_nsx`) REFERENCES `sanpham` (`ma_nsx`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_4wgoljn2csa1vdiy58t7twma6` FOREIGN KEY (`ma_nsx`) REFERENCES `nhasanxuat` (`ma_nsx`),
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`ma_SP`) REFERENCES `baohanh` (`ma_SP`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `fk_TK_NV` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_TK_PQ` FOREIGN KEY (`ma_PhanQuyen`) REFERENCES `phanquyen` (`ma_PhanQuyen`) ON UPDATE CASCADE;
COMMIT;


DROP PROCEDURE IF EXISTS USP_Login;
DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `USP_Login` (IN `tenDangNhap` VARCHAR(45), IN `matKhau` VARCHAR(45))  NO SQL
SELECT * FROM taikhoan WHERE taikhoan.ten_DangNhap = tenDangNhap AND taikhoan.matkhau_DangNhap = matKhau$$

DELIMITER ;