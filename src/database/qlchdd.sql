DROP DATABASE IF EXISTS qlchdd;

--
-- Database: `qlchdd`
--
CREATE SCHEMA IF NOT EXISTS `qlchdd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `qlchdd` ;

CREATE DEFINER=`root`@`localhost` PROCEDURE `DoanhThu` (IN `nam` INT)  NO SQL
SELECT * FROM doanhthu WHERE doanhthu.nam = nam
ORDER BY doanhthu.thang$$

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `PhieuNhanBaoHanh` (IN `maBH` INT)  NO SQL
SELECT baohanh.ma_BH, baohanh.sohd_Ban, baohanh.ma_SP, baohanh.serial, baohanh.yeucau_BH, baohanh.ngaynhan, khachhang.ma_KH, khachhang.ten_KH, khachhang.soDT_KH, khachhang.diachi_KH, sanpham.ten_SP, sanpham.thoigian_BH, hoadonban.ngay_Ban, nhanvien.ma_NV, nhanvien.ten_NV
FROM baohanh, khachhang, hoadonban, sanpham, nhanvien
WHERE baohanh.sohd_Ban = hoadonban.sohd_Ban AND hoadonban.ma_KH = khachhang.ma_KH AND sanpham.ma_SP = baohanh.ma_SP AND baohanh.nhanvien_Nhan = nhanvien.ma_NV AND baohanh.ma_BH = maBH$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PhieuTraBaoHanh` (IN `maBH` INT)  NO SQL
SELECT baohanh.ma_BH, baohanh.sohd_Ban, baohanh.ma_SP, baohanh.serial, baohanh.yeucau_BH, baohanh.ngaynhan, baohanh.ngaytra, khachhang.ma_KH, khachhang.ten_KH, khachhang.soDT_KH, khachhang.diachi_KH, sanpham.ten_SP, sanpham.thoigian_BH, hoadonban.ngay_Ban, nhanvien.ma_NV, nhanvien.ten_NV
FROM baohanh, khachhang, hoadonban, sanpham, nhanvien
WHERE baohanh.sohd_Ban = hoadonban.sohd_Ban AND hoadonban.ma_KH = khachhang.ma_KH AND sanpham.ma_SP = baohanh.ma_SP AND baohanh.nhanvien_Tra = nhanvien.ma_NV AND baohanh.ma_BH = maBH$$

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
  `yeucau_BH` varchar(1000) NOT NULL,
  `nhanvien_Nhan` int(11) NOT NULL,
  `ngaynhan` date NOT NULL,
  `tinhtrang` int(11) NOT NULL,
  `nhanvien_Tra` int(11) DEFAULT NULL,
  `ngaytra` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `baohanh`
--

INSERT INTO `baohanh` (`ma_BH`, `sohd_Ban`, `ma_SP`, `serial`, `yeucau_BH`, `nhanvien_Nhan`, `ngaynhan`, `tinhtrang`, `nhanvien_Tra`, `ngaytra`) VALUES
(2, 7, 1, 123456, 'hư loa', 4, '2018-12-29', 0, 4, NULL),
(11, 7, 1, 123456, 'ewqrưe', 5, '2018-12-30', 0, NULL, NULL),
(12, 7, 1, 123, 'sdDA', 5, '2018-12-30', 2, 5, '2018-12-30');

--
-- Triggers `baohanh`
--
DELIMITER $$
CREATE TRIGGER `insert_BaoHanh` BEFORE INSERT ON `baohanh` FOR EACH ROW BEGIN
IF(NEW.ngaytra < NEW.ngaynhan)
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Ngày nhận bảo hành phải bé hơn hoặc bằng ngày trả';
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_BaoHanh` BEFORE UPDATE ON `baohanh` FOR EACH ROW BEGIN
IF(NEW.ngaytra < NEW.ngaynhan)
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Ngày nhận bảo hành phải bé hơn hoặc bằng ngày trả';
END IF;
END
$$
DELIMITER ;

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

--
-- Dumping data for table `cthd_ban`
--

INSERT INTO `cthd_ban` (`sohd_Ban`, `ma_SP`, `sl`, `ma_KM`, `gia_Goc`, `tien_Giam`, `thanhtien`) VALUES
(7, 1, 3, NULL, 7000000, 0, 21000000),
(11, 3, 1, 5, 16000000, 2400000.0953674316, 13599999.904632568),
(11, 4, 1, 5, 5000000, 750000.0298023224, 4249999.970197678);

--
-- Triggers `cthd_ban`
--
DELIMITER $$
CREATE TRIGGER `insert_CTHDB` BEFORE INSERT ON `cthd_ban` FOR EACH ROW BEGIN
	DECLARE giaban DOUBLE;
    DECLARE heso FLOAT;
    DECLARE giabangoc DOUBLE;
    DECLARE tiengiam DOUBLE;
    DECLARE thanhtien DOUBLE;
    DECLARE tongtien DOUBLE;
    
    IF (NEW.ma_KM IS NULL) THEN
    	SET heso = 0;
    ELSE
    	SELECT km.hs_KM INTO heso FROM khuyenmai km WHERE km.ma_KM = NEW.ma_KM;
    END IF;
    SELECT sanpham.gia_BanRa INTO giabangoc FROM sanpham WHERE sanpham.ma_SP = NEW.ma_SP;
    
    SET tiengiam =  giabangoc * heso;
    SET thanhtien =  (giabangoc - tiengiam)*NEW.sl;
    
    SET NEW.gia_Goc = giabangoc, NEW.tien_Giam = tiengiam, NEW.thanhtien = thanhtien;
    
    SELECT hoadonban.tongtien_Ban INTO tongtien FROM hoadonban WHERE
    hoadonban.sohd_Ban = NEW.sohd_Ban;
    SET tongtien =  tongtien + NEW.thanhtien;
    
    UPDATE hoadonban
    SET hoadonban.tongtien_Ban = tongtien
    WHERE hoadonban.sohd_Ban = NEW.sohd_Ban;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_CTHDB` BEFORE UPDATE ON `cthd_ban` FOR EACH ROW BEGIN
	DECLARE giaban DOUBLE;
    DECLARE heso FLOAT;
    DECLARE giabangoc DOUBLE;
    DECLARE tiengiam DOUBLE;
    DECLARE thanhtien DOUBLE;
    DECLARE tongtien DOUBLE;
    
    IF (NEW.ma_KM IS NULL) THEN
    	SET heso = 0;
    ELSE
    	SELECT km.hs_KM INTO heso FROM khuyenmai km WHERE km.ma_KM = NEW.ma_KM;
    END IF;
    SELECT sanpham.gia_BanRa INTO giabangoc FROM sanpham WHERE sanpham.ma_SP = NEW.ma_SP;
    
    SET tiengiam =  giabangoc * heso;
    SET thanhtien =  (giabangoc - tiengiam)*NEW.sl;
    
    SET NEW.gia_Goc = giabangoc, NEW.tien_Giam = tiengiam, NEW.thanhtien = thanhtien;
    
    SELECT hoadonban.tongtien_Ban INTO tongtien FROM hoadonban WHERE
    hoadonban.sohd_Ban = NEW.sohd_Ban;
    SET tongtien =  tongtien + NEW.thanhtien - OLD.thanhtien;
    
    UPDATE hoadonban
    SET hoadonban.tongtien_Ban = tongtien
    WHERE hoadonban.sohd_Ban = NEW.sohd_Ban;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `cthd_mua`
--

CREATE TABLE `cthd_mua` (
  `sohd_Mua` int(11) NOT NULL,
  `ma_SP` int(11) NOT NULL,
  `sl` int(11) NOT NULL,
  `dongia_SP` double NOT NULL,
  `thanhtien` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cthd_mua`
--

INSERT INTO `cthd_mua` (`sohd_Mua`, `ma_SP`, `sl`, `dongia_SP`, `thanhtien`) VALUES
(13, 1, 3, 5000000, 15000000);

--
-- Triggers `cthd_mua`
--
DELIMITER $$
CREATE TRIGGER `insert_CTHDM` BEFORE INSERT ON `cthd_mua` FOR EACH ROW BEGIN
	DECLARE tongtien INT;
    
    SELECT hoadonmua.tongtien_Mua INTO tongtien FROM hoadonmua WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
    
    SET NEW.thanhtien = NEW.sl*NEW.dongia_SP;
    
    SET tongtien = tongtien + NEW.thanhtien;
    
    UPDATE hoadonmua SET hoadonmua.tongtien_Mua = tongtien WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_CTHDM` BEFORE UPDATE ON `cthd_mua` FOR EACH ROW BEGIN
	DECLARE tongtien INT;
    
    SELECT hoadonmua.tongtien_Mua INTO tongtien FROM hoadonmua WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
    
    SET NEW.thanhtien = NEW.sl*NEW.dongia_SP;
    
    SET tongtien = tongtien + NEW.thanhtien - OLD.thanhtien;
    
    UPDATE hoadonmua SET hoadonmua.tongtien_Mua = tongtien WHERE hoadonmua.sohd_Mua = NEW.sohd_Mua;
END
$$
DELIMITER ;

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
(4, 4),
(5, 1),
(5, 2),
(5, 3),
(5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `doanhthu`
--

CREATE TABLE `doanhthu` (
  `thang` int(11) NOT NULL,
  `nam` int(11) NOT NULL,
  `tienban_SP` double DEFAULT '0',
  `tienmua_SP` double DEFAULT '0',
  `tienluong_NV` double DEFAULT '0',
  `tienloi` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `doanhthu`
--

INSERT INTO `doanhthu` (`thang`, `nam`, `tienban_SP`, `tienmua_SP`, `tienluong_NV`, `tienloi`) VALUES
(11, 2018, 0, 0, 4123, -4123),
(12, 2018, 248599999.1774559, 15000000, 7462123, 226137876.1774559);

--
-- Triggers `doanhthu`
--
DELIMITER $$
CREATE TRIGGER `insert_doanhthu` BEFORE INSERT ON `doanhthu` FOR EACH ROW BEGIN
	DECLARE luong DOUBLE;
    SELECT SUM(nhanvien.luong_CB) INTO luong FROM nhanvien WHERE nhanvien.tinh_trang = 1;
    SET NEW.tienluong_NV = luong + NEW.tienluong_NV;
    SET NEW.tienloi = NEW.tienban_SP - NEW.tienmua_SP - NEW.tienluong_NV;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_doanhthu` BEFORE UPDATE ON `doanhthu` FOR EACH ROW BEGIN
    SET NEW.tienloi = NEW.tienban_SP - NEW.tienmua_SP - NEW.tienluong_NV;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `hoadonban`
--

CREATE TABLE `hoadonban` (
  `sohd_Ban` int(11) NOT NULL,
  `ngay_Ban` date NOT NULL,
  `ma_NV` int(11) DEFAULT NULL,
  `ma_KH` int(11) DEFAULT NULL,
  `tongtien_Ban` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hoadonban`
--

INSERT INTO `hoadonban` (`sohd_Ban`, `ngay_Ban`, `ma_NV`, `ma_KH`, `tongtien_Ban`) VALUES
(7, '2018-12-28', 5, 1, 21000000),
(11, '2018-12-31', 5, 6, 17849999.874830246);

--
-- Triggers `hoadonban`
--
DELIMITER $$
CREATE TRIGGER `insert_hoadonban` AFTER INSERT ON `hoadonban` FOR EACH ROW BEGIN
	DECLARE flag1 INT;
    DECLARE flag2 INT;
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienhh DOUBLE;
    DECLARE hs DOUBLE DEFAULT 0.05;
    DECLARE tienban DOUBLE;
    DECLARE tienmua DOUBLE;
    DECLARE luong DOUBLE;
    DECLARE tienloi DOUBLE;
    
    SET nam = year(NEW.ngay_Ban), thang = month(NEW.ngay_Ban);
    
    SELECT COUNT(*) INTO flag1 FROM hoahong WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
    
    IF(flag1 = 0) THEN
    BEGIN
    	INSERT INTO `hoahong` (`ma_NV`, `thang`, `nam`, `tien_HH`) VALUES (NEW.ma_NV, thang, nam, NEW.tongtien_Ban); 
    END;
    ELSE
    BEGIN
    	SELECT hoahong.tien_HH INTO tienhh FROM hoahong WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
        
        SET tienhh = tienhh + NEW.tongtien_Ban*hs;
        
        UPDATE hoahong SET hoahong.tien_HH = tienhh  WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
    END;
    END IF;
    
    SELECT COUNT(*) INTO flag2 FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
    
    IF(flag2 = 0) THEN
    BEGIN
    	SELECT SUM(nhanvien.luong_CB) INTO luong FROM nhanvien WHERE nhanvien.tinh_trang = 1;
        SET tienloi = NEW.tongtien_Ban - luong;
    	INSERT INTO `doanhthu` (`thang`, `nam`, `tienban_SP`, `tienmua_SP`, `tienluong_NV`, `tienloi`) VALUES (thang, nam, NEW.tongtien_Ban, '0', luong, tienloi);
    END;
    ELSE
    BEGIN
    	SELECT doanhthu.tienban_SP INTO tienban FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        SELECT doanhthu.tienmua_SP INTO tienmua FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        SELECT doanhthu.tienluong_NV INTO luong FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        
        SET tienban = tienban + NEW.tongtien_Ban;
        SET tienloi = tienban - tienmua - luong;
        
        UPDATE doanhthu SET doanhthu.tienban_SP = tienban, doanhthu.tienloi = tienloi WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
    END;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_hoadonban` AFTER UPDATE ON `hoadonban` FOR EACH ROW BEGIN
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienhh DOUBLE;
    DECLARE hs DOUBLE DEFAULT 0.03;
    DECLARE tienban DOUBLE;
    
    SET nam = year(NEW.ngay_Ban), thang = month(NEW.ngay_Ban);
    
    SELECT hoahong.tien_HH INTO tienhh FROM hoahong WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
        
    SET tienhh = tienhh + (NEW.tongtien_Ban - OLD.tongtien_Ban)*hs;
        
    UPDATE hoahong SET hoahong.tien_HH = tienhh  WHERE hoahong.nam = nam AND hoahong.thang = thang AND hoahong.ma_NV = NEW.ma_NV;
    
    SELECT doanhthu.tienban_SP INTO tienban FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        
    SET tienban = tienban + NEW.tongtien_Ban - OLD.tongtien_Ban;
        
    UPDATE doanhthu SET doanhthu.tienban_SP = tienban WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
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
  `ma_NV` int(11) NOT NULL,
  `tongtien_Mua` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hoadonmua`
--

INSERT INTO `hoadonmua` (`sohd_Mua`, `ngay_Nhap`, `ma_NCC`, `ma_NV`, `tongtien_Mua`) VALUES
(13, '2018-12-28', 2, 4, 30000000);

--
-- Triggers `hoadonmua`
--
DELIMITER $$
CREATE TRIGGER `insert_hoadonmua` AFTER INSERT ON `hoadonmua` FOR EACH ROW BEGIN
	DECLARE flag INT;
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienmua DOUBLE;
    
    SET thang = month(NEW.ngay_Nhap), nam = year(NEW.ngay_Nhap);
    
    SELECT COUNT(*) INTO flag FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
    
    IF(flag = 0) THEN
    BEGIN
    	INSERT INTO `doanhthu` (`thang`, `nam`, `tienban_SP`, `tienmua_SP`, `tienluong_NV`, `tienloi`) VALUES (thang, nam, '0', NEW.tongtien_Mua, '0', '0');
    END;
    ELSE
    BEGIN
    	SELECT doanhthu.tienmua_SP INTO tienmua FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        
        SET tienmua = tienmua + NEW.tongtien_Mua;
        
        UPDATE doanhthu SET doanhthu.tienmua_SP = tienmua WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
    END;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_hoadonmua` AFTER UPDATE ON `hoadonmua` FOR EACH ROW BEGIN
    DECLARE nam INT;
    DECLARE thang INT;
    DECLARE tienmua DOUBLE;
    
    SET thang = month(NEW.ngay_Nhap), nam = year(NEW.ngay_Nhap);
    
    SELECT doanhthu.tienmua_SP INTO tienmua FROM doanhthu WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
        
    SET tienmua = tienmua + NEW.tongtien_Mua - OLD.tongtien_Mua;
        
    UPDATE doanhthu SET doanhthu.tienmua_SP = tienmua WHERE doanhthu.thang = thang AND doanhthu.nam = nam;
END
$$
DELIMITER ;

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

--
-- Dumping data for table `hoahong`
--

INSERT INTO `hoahong` (`ma_NV`, `thang`, `nam`, `tien_HH`) VALUES
(4, 11, 2018, 0),
(5, 12, 2018, 6828000);

--
-- Triggers `hoahong`
--
DELIMITER $$
CREATE TRIGGER `insert_hoahong` AFTER INSERT ON `hoahong` FOR EACH ROW BEGIN
	DECLARE flag INT;
    DECLARE tongluong DOUBLE;
    DECLARE luong DOUBLE;
    
    SET luong = NEW.tien_HH;
    
    SELECT COUNT(*) INTO flag FROM doanhthu WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
    
    IF(flag = 0) THEN
    BEGIN
    	INSERT INTO `doanhthu` (`thang`, `nam`, `tienban_SP`, `tienmua_SP`, `tienluong_NV`, `tienloi`) VALUES (NEW.thang, NEW.nam, '0', '0', luong, '0');
    END;
    ELSE
    BEGIN
    	SELECT doanhthu.tienluong_NV INTO tongluong FROM doanhthu WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
        
        SET tongluong = tongluong + luong;
        
        UPDATE doanhthu SET doanhthu.tienluong_NV = tongluong WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
    END;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_hoahong` AFTER UPDATE ON `hoahong` FOR EACH ROW BEGIN
    DECLARE tongluong DOUBLE;
    
    SELECT doanhthu.tienluong_NV INTO tongluong FROM doanhthu WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
        
    SET tongluong = tongluong + NEW.tien_HH - OLD.tien_HH;
        
    UPDATE doanhthu SET doanhthu.tienluong_NV = tongluong WHERE doanhthu.thang = NEW.thang AND doanhthu.nam = NEW.nam;
END
$$
DELIMITER ;

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
(5, 'William Sydney Porter', 32156, 'Mỹ', 777887, NULL),
(6, 'long', 147258369, 'hà nội', 123456, 'thăng');

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `ma_KM` int(11) NOT NULL,
  `ten_KM` varchar(45) NOT NULL,
  `hs_KM` float NOT NULL,
  `ngay_BD` date NOT NULL,
  `ngay_KT` date NOT NULL
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

--
-- Triggers `khuyenmai`
--
DELIMITER $$
CREATE TRIGGER `insert_KhuyenMai` BEFORE INSERT ON `khuyenmai` FOR EACH ROW BEGIN
IF(NEW.ngay_KT < NEW.ngay_BD)
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Ngày bắt đầu khuyến mãi phải bé hơn hoặc bằng ngày kết thúc';
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_KhuyenMai` BEFORE UPDATE ON `khuyenmai` FOR EACH ROW BEGIN
IF(NEW.ngay_KT < NEW.ngay_BD)
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Ngày bắt đầu khuyến mãi phải bé hơn hoặc bằng ngày kết thúc';
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `ma_NCC` int(11) NOT NULL,
  `ten_NCC` varchar(45) NOT NULL,
  `diachi_NCC` varchar(45) NOT NULL,
  `soDT_NCC` int(11) NOT NULL,
  `tinh_trang` int(11) NOT NULL
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
  `ten_NV` varchar(255) NOT NULL,
  `soCMND_NV` int(9) DEFAULT NULL,
  `gioitinh` tinyint(1) NOT NULL,
  `ngaysinh_NV` date NOT NULL,
  `diachi_NV` varchar(45) DEFAULT NULL,
  `soDT_NV` int(11) DEFAULT NULL,
  `ngay_VaoLam` date DEFAULT NULL,
  `luong_CB` double NOT NULL,
  `tinh_trang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`ma_NV`, `ten_NV`, `soCMND_NV`, `gioitinh`, `ngaysinh_NV`, `diachi_NV`, `soDT_NV`, `ngay_VaoLam`, `luong_CB`, `tinh_trang`) VALUES
(3, 'Hồ Thái Thăng', 123456789, 1, '3898-02-01', 'ko có', 1234, '3918-11-01', 1000, 1),
(4, 'Loui Pasteur', 345345, 1, '1822-12-27', 'Pháp', 12345, '2018-11-01', 1000, 1),
(5, 'Thomas Alva Edison', 7878, 1, '1847-02-11', 'Mỹ', 567432, '2018-01-01', 1000, 1);

--
-- Triggers `nhanvien`
--
DELIMITER $$
CREATE TRIGGER `insert_NhanVien` BEFORE INSERT ON `nhanvien` FOR EACH ROW BEGIN
IF TIMESTAMPDIFF(YEAR,NEW.ngaysinh_NV,NEW.ngay_VaoLam) < 18
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Nhân viên phải đủ 18 tuổi';
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_NhanVien` BEFORE UPDATE ON `nhanvien` FOR EACH ROW BEGIN
IF TIMESTAMPDIFF(YEAR,NEW.ngaysinh_NV,NEW.ngay_VaoLam) < 18
THEN
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Nhân viên phải đủ 18 tuổi';
END IF;
END
$$
DELIMITER ;

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
(3, 'GHI', ''),
(4, 'SamSung', 'Hàn Quốc');

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

INSERT INTO `sanpham` (`ma_SP`, `ten_SP`, `ma_nsx`, `sl`, `nam_SX`, `gia_BanRa`, `thoigian_BH`, `xuatxu`, `mau`, `bonho`, `kichthuoc`, `anh`, `tinhtrang`) VALUES
(1, 'iPhone 6 32GB', 1, 7, 2017, 7000000, 12, 'Trung Quốc', 'Hồng', '32G', NULL, NULL, 1),
(2, 'Samsung Galaxy A9', 3, 10, 2018, 12500000, 12, NULL, 'Đen', '128GB', '6.3 inch Full HD+ (1080 x 2220 Pixels)', NULL, 1),
(3, 'Samsung Galaxy S8', 2, 8, 2018, 16000000, 12, NULL, 'Xanh', '64GB', '2K+ (1440 x 2960 Pixels)', NULL, 1),
(4, 'Oppo neo 9', 1, 5, 2017, 5000000, 12, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sanpham` (`ma_SP`, `ten_SP`, `ma_nsx`, `sl`, `nam_SX`, `gia_BanRa`, `thoigian_BH`, `xuatxu`, `mau`, `bonho`, `kichthuoc`, `anh`, `tinhtrang`) VALUES
(5, 'Galaxy Note 9', 4, 2018, 2018, 22060000, 12, 'Hàn Quốc', 'Midnight Black', '128GB/6GB', '6.4 inch 2960 x 1440 pixels', 0x89504e470d0a1a0a0000000d49484452000000960000009608060000003c0171e2000070b44944415478daecbd05749557baff5f88bbbbbbbbbbbb1021018284282109810811e22e4420218440428810c35d5a0a454aa154a6940a53819652993bb3eeffdeb973e7da7c7f7bbfe79ce424444eb0b2fe6bb2d6b38ebf2739e793eff3ddcf7ef67edfc23f7ffef9f30a7edefae747f0cf9f7f82f5cf9fffff81f57ffff77ff8fbdfff8efff88fffc05ffffad7a9f8f77ffff719b767c7628f7307f7b1e9f5f982d7f7a0f72f14fff66fff3615dcf7d3f7f8dbdffeb668fce77ffee733413f235e62aed7d258ec3d17fa5c389f0de73a7d3efddede48b07efcf14774b477202f2f0ff9f9f9282a2ac68e1da5a8aead434b5b3bca2bca51595181aaaa6a5457d7a096dccf89baba7a266a49d4d7b3a2a1a1098d8d4d686a6c465353339a9b5b986869d9c9c4ce9dad686d6de38af6a9ebf4b19616f2fc267a8c66e658f5f50de4bdea5153534b7e871a545656a1a2a212e5e515282d2d2751c6fcbe757575e4be72646565213b3b1b5bb76e65fea6ed8545a86f6ec58ed20a1417d3bf6d07794de9d4655959d933418f535e5ec95c5690bf7d3aaac8fb57325155553523aaabab67dca6cfe1bc8e1e871e97be270dfa7b141515a1b0b010dbb76f67a2a0a080b9a4df01bd4e83fefeb9b9b9d8b66d1bf3f7d43534a3a878073233b390919181b4b434a4a7a793cfae95f91edf18b0befaea2b787a7ac1c1d1056e6edef0f50b4650500482834984ac40485834024322c9ed48d67dc191531112c20a7a3d88b95cc18ee9e70705738e35fbbee9e3d0c766df1734f57e11335e37753caefb69c4c6ae465858380c0c0ca0a6a6c684a6a626b4b4b4a0a5ad056d1d5d72a9cbbaada5c384b6b63613acfbe60a9d19b759cfd559e0f90b1de7795e37f3bdb5b575a043fe0e6d2dfa7be8327f9fbaba3af3b7d2e7787878e0e1c387bf3f585442cb2b2b10bf663df9e00da0a2aa092b3b7b985858c2c0c414fac6264ce81991eb862630303486be81d154e8e91b32a1a36f005d3d03e8e8ea33a1ada3c704f345b243937cb09cd0d0d466425d436b46a8a96b32a1aaa6c1848aaafa8c5056519b0a2565d5a9505452414ccc4af2df9b01434323080a0a829f9f1fcb972fe729f8962df4f8329e8ff37b041f1f1f04040420232343942b93a86205fef18f7ffcbe6051afb12db7009bb3b6414a5a9e09475737189b5b30607182c245a1e2068b03150d0a95b61e0b280e5c0b41351f481c98b801e24044e1e18482a23213f20a4a4cc8ca292035351d7d070e4157479ff9c0972d5b364f2c8700bf08b9e463ae8b084b404c42967c394253cf11101066823eceb79c7fdee3898a8a425a5a9a842c135252324c706e4b4a4acefb5a0a84a2821ac4c5a59e798cfe2ec2c2a20bfc0d3383be077dafb2b22a94ec28637cd7ef0ad66fbffd09c9a9244f6fce26bf9834014b164eaeee303233e7522b0213856a1ea0384aa5cd56aaf9805a4899b8d569b622cd0514375872f28a909195474949298647c69974b11058fc022250d6b02197420c38d2d26ae45846e418aaec2f891f12e20ae40b5720d7052029ae480092649e3bfb58b2b2b2505555e50a9a963498a0d79594941880e6fa3dc4c424c83f991954d4f49e798eb8b8380326555d5ec1929090201eb21c8d4d2df8cb5ffef2fb82f5d34f4fb1212915a9042c090929062c076757189a9a3140718242a5c7051537504c1095d0d4d57b06a6d9698f031437580ba5b9d93071148a03140daa56f4b18aca6a062c2de23f16026bf95b347d084f3d474c5c0ef24a7a101193663f870f2222d24431a41898040545c9172cc0b37af01afcfc82e4f756858282ea9c6ac6ada0bc804561a483939d3b3bf0a73ffde9f705ebc9939f08586948cdc89a02cbced985016baeb4c7014a97a63abd693fb558ca9b0dd56cffc40b54dc0ac50d150dfadcaaea5a3658bae48b11225f9c30f8994b21e6928f4f808420fb927d7d39fb3a93eef8994b3e3ed6f5e9e09bbe5cc67d9b9febf6dcf7b1546eda0fcda57a2f23a6c1aa24a3c35d2413fdf6fb82f5e38f4fb061631a930a3960d93a39b37cd502698fdba42f45a516f352f3a5bdb9548a13340dd2e754d7d461e4f0044c4deca1afed02031d57e8693b425bc30e5a6a36d054b38486aa15d4942da0aa640a65052328c91b40515e070a72da9097d5829c8c0664a5352023a50e6949354849a8925022e99015126224c465991017938598a81444452488c24940588886388404c59810141025c1b9a421c2783b010a3a3f0770be1946fc45c12a2d6381f5ebafbffede60fd88f504ac14aa586c8f65ebe80403e2ad7479008a5773be904acd1edd2d05aad9600d8f4cc0d12e1886baeed0d37286aea603b4d56c0954d60c54ea34942cd96019b2c1d22560e910b0b409589a4c50b828582cb89421c580a5cc064b8e0d961c818a8225c90c00b8a19a066b3a58508930298ea5a0fccf80f5bc70712b565bdb1ba1583f12c54a6752a1385bb16c1c1c19d3ae3b0754b3cb080b951078f152b3477c0ba5beb980e2c4b4624d326019e8b83050e968d8336069a95a43538540a562093502950a31ebca0a042a39fd1960b1548b82c50a6949aa5caa909124604928f10cd66ca866c02520c495925f1e58d4bc979655a3adb5f3f757ac2714aca474a258990c589264a86c4dc0a2867d31a55a8a9f7a9e51df42507183252d43ccb782326a6a1b3132320907db00e86b12b55277848efa34541aca54adcc0858c68c5a2913b55252d065a7421d365cec9066858c14498b0c5cca4c4a94105366432543d2a0f40ca816038b059520512b61b6af63f9be692ff7e260b1148b82f5a73741b1482adc340d9695bd0303d66ca038b158a1733ea8e652aaa58cfc66434581e2849cbc1251ac062615da336039425783a6419b69b52250d1505564792b0a96a29c2e130ab27a2cf5923362858c01146474204bc09295a060a910c5922750c9cf0bd6426950905d1763d220f557cf40c5ffc21e8ba558956cc5fad39b61de2958d4bc539f45c1d235345ad45371a05ac8a42f36ea9b2ffd71a05a4ca968d0f42dafa03865dea962e99134a8cba44162da49fad320865d4dc984782b1396b752d087921c512c397a49d44bc184091579e2bde48ca128abcf024b521332c4c04b8b539fa5c065dce5a6c0e245ad18c52250b1d22047ad845e8a5acd50acb20ab4b775be09e69d82958a94f4cd8c62b1c0b29f01162f9e6a2923bfe729272c04150b2c2506acc344b11cacfd0958f653699042a5ae640e354513c65b3150c9138f2547409327a95181fa2ea2682455aa295a10b84ca6c0929322609134282da140c0e2562b59c65f2d9a060545989832edfcd3e58e97a5563353610551acdd6fc6a8909af79474920ac52599ea3b07acb98cba86363b9ec3a8bf8852cd068a1b2a1af435b48e453d96bd952ff4b80d3b2705124552553026614a82de47a1738096b20bb4541ca1a96ccb1a3192c7a99229c8905122e3b1586a2529a648429ead56b2042ade4c3b2705be1eb02adf0cf3fe23dbbc27a7b1c0a28a656947fedb0d0ce755aae72929f032fae365043817541cb098544814cbced207baaa366cc36e3905162b2ca04180d254266952d595187c4fe86b784157cd9d80680f2d02963a512c25eabb0858b29233c1921095638f066518b07833ed5c6970b9d02c7fb5fc85a19a4bb1de9872034d858cc722616167076d7d83450b9f7341452fe9eba6cdbe3e135ada7a53a1a94582ebb68626f77167c6ec29a0e9f7d222b06a30b0d101871c818f0396bd85177454aca1ad6cc5a4410d657306304d656b68a93940874064a0e10723ad20186b07c050cb97c0e546ee277f370551c1082ab2ba5092d1624685d2b4d4c0a895c22cd32eb9b069a76990abc43065da5f2158a565556f462a7cc21e1526538f45158b80656e673b05d6528a9ff43e57370fd83b38c1d6ce81091b02a92d49adb66440c00451431b12ac4b3be63e3b074738383ac3c9d915ce2eac70a2e1ec02472756d063d2b0b3779c11f4751c859b4a85042c5d55f237109034a97291eb3a6a4ed023ea64a0150063dd5098e947c2dc20925c06c344d707469aaed067c02230927449c15294a660a9428a018b002c26c780c5f1568ba641419129a59a3f0dbe7cb068e5fd975f7ef97dc1fa810316271552b06c5960f1aa543468da333523269936a29134aa4b53a9810174e82080842e57e8701e2397c69696d0373625cf27b7f50d99f9471a74429b3b34747459c15c27418057d322bf13093a3b40958b05d6041c2d7d61a8ee4c5489c0a2e109434d3f18eb04c2543f021686b1b0365e056b131a31b0340c85b99e2f4cb45cc8f3eda04b606481a507795a20955262a74132221495659bf6a51744a7a11262cf45f2bd146ff52c581568ddb9fbcd18152632606d9e0116f3e52ea1004a3d94a1910934c9976f626d0ddfa02098d09e2e721f6db9e140c5145ec9a589b515e2d7ad87a9b915cc4cad604ce0d2a7cd82143a72c928e61c80b93911a5f20a86a38d190398b2ba060cc9f165b952a18b75184c75fc614a95498fc0a41f0b2ba355b035590b7bf34438586c20b1160ee6b1b033098395812fcc755d61a2e1083d924235e8e8912896bcb43a512c165812a21cb06478038b4983c233a770d86970e6e4f6cb05eb0df35804ac541658b4e460666d3303acc5da5e6850634e95832a514a46064c8d09547a04262d03a6a586bb0547c7c8082b56c691d4e6024f0f3fb8387bc2ddcd0776368eccc4373d06a398042407677bc4c684c13fc09bfc4eba58171b8db03549880af1243e4d07ca1a1acc6ba862d202294d856e0eab604595c9249e81c9ce8cc064990447ab14385ba7c2d52689c43ab858c5c1c93c12f62601042e0f986b3b93d124499d8aa6d360b1d3a0041d098a3d4fa59dcbb4b3a7715ed64870be02294bb1de00b0129951e1dc602d960239a3400e587ac6268cefb134b1858599232cf40848c4acd36e8929b84c4c604dfc97a73b81c9d615063aa604000b062e135373720c63165844fd2e9ce8c75fbe3b81efef5f8425499b666636c49f39c28480aba9a303150d4d062c671737920aeb31323c096fe764d89b1255324f266931054ed6e970b2dd0417fb4df0b0cf80a74332bc1d12e165b71aee36d170b60c869db13f2c75dd480a256a4dcb1132645428a50669097689419405164fde6a8e34c89ac6117ca92586f9ca0d6f482a648195949a01310a16093392ca185fc3c3740d775981512c028401b9b4b170808b8d17ca5373a0ada9077b62c075d960d15448d3a6af57006cac5dc997ea88bd550df070f185b5b52d8c2d2cc9710c49aad3c1b50b87f0d79f4ee0cf3f7d001b1b6bb8395b6375b4379c9d6c9854a8ca06cbd59582450ba447e0eb920e478b643859a51185da0c57db2cb8db67c2c331934097053fd74d08704d819ff33af83aad82876d3451ae30d81878c1944e032999424d46070a922a240db247837424283a774194a74e8697382fb858dbcc1b950a9338a99084a9d5b3602dd6f939950a75f5994b7d1d4318e91ac3ccc0149e9edef00f09859e21573a24003a39bac3c3dd0f76d64e880c0e27b0b8232a7625a3681c139fb03a0a2db5db91bf6d1374740d88aad1d7f89214ea4a7e475d062cfa7e14acca2a62de0f1f819f0b4979164970b64a257067c2cd2e9b409543a0ca819fdb5604ba6721d42b834432823d3612d012e04994cbd1d80f165aced05732873a4d8512d360898910b522a3410e540b56dab90aa274d29942c52935bc4ab0a615eb0d699b4964c0ca607ab039603123b0054a0b738145958302491589d3796a6d6787c8b8d8a9def9a991217d0e095b1ba26c0428eaaf42c322101c193965dcb9831e579b8493bd39c2fc1c606765ccd4c054c8ef43dba65d5cdc0958758cc7f2735e0b27d3357036df48e04a6714cbc3611b7c9cf3e1ef968720af6d88f4db8228ff0cacf04b4738012cc83901ee1644b5f4dc6144e716e5085892ca90a4d33822b22cb084973e2fc8cff8aad9de8aefa54235a31f6b47c59b0c961503d6525a60a64685e4cba76ac494188849d727f74d951a0ca6cb0d3aecd11fbd6d4014ca8cbca7a5ad1db40ce6180d92df45932935e8428b591bc89a565267d7d86821d6790aac09f8db87c1c52008cec63170315b07578b14b8db66c28bc0e5e7528020cf7c44f8152036681be282b6202e2013919ea9f07788879309194dd2c96b390362ded520c5f82b59a256d4b48bf3d0c530bbc420f84a4dfb338ab5838e0adf20b036a66c62c0129b05d6523a166831d5c7d79f295c5a11afc41dd6ecb0b1b18395950d139696d6b0a497f4367dccd61ef6b4e8e9e0c4841d31f89cb0258fd1c7adc9eb695891a0055837774f664a88a6c26a3658110ec1f0d07581ab8e3b5cf5fde16a1405770298bb751a31ee241dbae623d4bb003181055815968f84b06d880fcc428467223cad2260a5e70a3d45132852f32eca068b67b512996e8f611b7656a7a8c02b4981dcf1c6a5c28dc4bc2f06d642cbb4b8e701e96bcccc2da70060820b2c1a566c90acd85071c2828246c2821de616563027c7b2a097eceb66eca0c5589a6a69fd8a8e64a7c0221e2b252a036bdc5722d62a088186eef0d27182879e373c8d56c0cb7c03190d6e86bfeb5684fa6c476c48111222b6634d781e812b13c12eabe164e60763354b28cb68b0144b8477b004b8faaea64b0cfcaf54ad9ef1586f8262712aef142c5136582664584f2be84b81eac5da8be9c4b22c13b32797e93c209d18e70e0ad2ec7075752760d5e3f0e163484baa41e2ea5224c71620293c0deb3da309647e08367085afbe27bc4c08603649f077de8a489f22c48716617d6411d645e421d63f157e0e2b60a5eb0875793dc8882b3145d145e70517ac5df1bf72b5e22816674ae78d002b71633a9253581e8bc67c602dd4b3bed48510f43ed6ebe97135984b6515f5a9e3703a182858dc70cd0515fd67a0605555d663f4f0096c4adf89e4c44624afab43d2da2aa4ac2d434a5c2ed24292b0d629142b8cdc8992f9c0d7221e014e240512b8d684edc086e862ac0bdf8615de1be06ae60f7d6513c849aacc09d6bc50cd59bbe27b6d60b1da6676bf291e2b1d29a9d360195b584c8135d71e0abc2ed99a4fade8759aea683ab3b4b28383239d707663c2d1c98da9c8d354c9e95ce0052c5a20a5e67d74ec04b664772325ad0369a9ede4ef6a455a720b520968a91baa91be760732a248ea770d4794b127024dc311e040468704aeb591a5d8184d200bdd8240a7185868db4345460b1262b28b8325c8f656538b2504e69c707e5550cd9e847e734685c92c8f25ca068b4ef0be8c16e38516433c7bff74cf15e73d164b8314aa99609d445ee1016cc9eb41f6d61e6cdeb207e919bb91b66917d237b56313012d3da9019b36542273452ad6dbf921c22400c176c988f22b4662543912a30bb1322095a8962f74140d202daec080c5f36890b358e277008bce15be19e6fdc913b6c74a67a0a261643e13ace75db6b5105834cd2db440821e93be272f50b1c07267a674c68862e56f3f80addbf62327af175bf359b1655b2f32b7ec2590ed4146461732d2db9091da882deb0a91e11d8b58537f84d8a7222eb81429b1a558bf220f21ae2b61a66503792965668e70d166be45d4ea5582359d0a09586f422afce1c7f9c17a5eb59a0dd55c2b97678335bb43743658f3a5401af47776766629d6d8e831d4e4b6a2a4a01b85457dc82dd88f6d797dd89a77805cef2797bdc8224a9691b90799595dc8242a9695528bace0d58837f543885326d64556104fb603ab8233e062e60d35796d48509f2528310758e4b6204d815c2586e542af55ad669af737acf2be3179131758e650d5d4e219aaa52e87a7f7718335573f3b3d3e27152e0415072c172615d6607cec38ca129b51145e88f2a86da84d2a4355de6e9494f423bff020b61510c8f269aa240a96b317d9d9246566eec6d6b4066c0d4d409c6900c2dcf391b4b202c931db11e11e0f23754b323a5480e89c60714c3bbbc4c037bd4fc4ab9a705e10acd237aaf29e8e440a96a838e3b33860f192029f67393c7d9c763158ccae5991e0d4a8e875fafcc552e09462b1c19a248ad55bba073d3b7ad04e5260596207b68717a332220bf59b2a50b1631f8a8b87880f1b404e7e3fb6e4f611c048aaccde83ad9b9bb025200651e6118809ae44faea0aac0dcf8293891794a4d5212a2cb5c884b320ab916f8ed6e3d7a7586f18588c7917652996a1d934584b816aa1e55bb355893e97ce45725a7358bd5f7aec7db4b499d7f2920239c131ef93c393e849484177522ef66e6b44df8e2ef455f4a2795b1f0a621b511a9081e6e412549612c04a0960db07919d4b0023706dcbd983dc8c5a64bb0421cc3a19497175485f558270b778682be933a3432141f1a95ef6191b7dcc6a3d9e068befb54035d363bd29957706acf429b00c4ccd983ea7c5b6667c919536b30ba14ccd4a5a9a1552f3fbaab9c012214acbe9c71a1f3e825a9f95e8b273c63e0f4fec8b5e87bd59a5e82bdd8543d5fbd05e4852624c132afc93d1925581ca8a43d85e32846c62f6b71105dbba750fb66f2c44b2850f22bd4a90bdbe1e491139b0d577848ca402b3a3ccb32b7038fe6a2ed3cec76c35f9bac02a2dad7a33cc3bab353995e5b1d86019b1c17ad92970a1e55b9c7a152f35abd950d1a00b319849e8d1534858770c213e7b91ea9089166b77f4d9db617f441c7ab3cad15fd98d43b5fd68cc1bc036bf42b4c6a5a396285a69d920f15e03c48791cbad1d2858b11e31a631488adf892d6b2b11e11c05451935880a492ed2d3ce8a654bf556749fd365acfd4e99fd50d9b16c392b78390e05abe44df158d7ae5dc3c6a454c663897052210f602d45ad165b6cba940afbec14c8018bce5156d7d4636cfc14ea9aafa2b8f25dac4bbd80c080416c70c8419ba533fa1c6dd1bf6a030e14b662b8ee00f6570d61eb8a263404ad43134999a5e5c304ac311490d1e4f62d2dd8e2e88330d7edc84dd989d4c8ad30503386b8a8d4f4b22e81f957382fe3e35b5a1a5cfe16c40848ba44fdbcc4a4b1424a1eb1522a0895908795881824e8f116d8b48d53c72a61971bce9f3fcf6c5cfc5ffff55f78f2e409ae5cb9827ff9977f797d601d3b769c28560a3630608933069eb6112bcf028b5ba91652ab85b61ae215aac50cfb5c60d1f44953e161a2580579c751b8e322ca1baea1a4f62ae2d69f83af07f150562b71c0dc0803fe013890538591da3e0cd50d217775176afc56a3a9b493c035829a8609e4e6eec58e75d9586514868dabdb90bfb116ded6fe90225f34c7c073cf0bb2468282cf8c06e70581bdc92e552775f29a5809393419da62d8310c076dc2d1631a846eb330f41a45e2a05618bad59c90a2a002752161d62ece7382258512da8fd5b61bed1dbb30387888d99e5b4f4f0f090909f8f2cb2f5f1f58478f12b0925298f664512eb068719417b5e23505cac8ca429ac62b502b6eb026c64fa3befa1c3293879195790485a59751dd7a03d98557e1157404abedb6a1dbd418036e8e3890594c94ab0fc38d83d8b6aa0b8d04ae868abd04ae211495f4a338bf0bdbdd82481acc4771e62e24856540455a032242e23cf45d2d9cbef8889a2912154a949147bb7d18f6841660223a17551e9b10631309174377986bd9c352c7117ea67ed86c178d1ed7288c393a234bdb14f22252ac74f9d6322628a092e252acee0602d6c0a141d4d5d5c29c8cf0e906bb252525af37151e3b7682016be32cb054d43596bc31da8229900dd6f340c5016b3ea818b024a5997585470f4f609c8c08076b0ea1a6ea3c32369274977716158d5751527713011167106a5f85dd262638e46887fecc1d1869ecc750e308b2235bd11ab206f555b4904a0bab3da848d98e04e320642412539f5a076b6d1b880a4bced89361bac4c0cf53a59d8fdcef282c8a1a4327a4b9ed40414c379a82f360afe74a060762d0555380a1963a8cb535a1afa1063d0d1528c9b3faedd5958c1160168258e734682bb84242480142cb0520482095949064ca0d6ded9d983c7a0cefbcf30e52535319c55ab76e1dfee77ffee7f5839594b4e959b078dcc2f17937f278596ac50dd691b1a318cbda8113213138969a8b7d9def203bf930b2334f3069b1a2e936fc23cf20c4b6127b8cf4887239a03faf1a63cd87d0df7018193ea5d815bf1135b583c8cae94549f15e147b0421caab1cd5b9dd58e911cf54e1850445599ba8f10b7035f32d5e10a52ae32e2c8c6a4b7fc4fa55217bf50154046e832cf1533a2a0af0b7764095a93fceb947e07dff105cf5f2c571274fe2119db0d1dc8680ad0e45593232e51782b404f9c797d68192843264087432e43360ca0d6d9d384232d1b7df7e8bcccc4cf8f8f8203a3a1a376edc78bd1e6b6372ea0cc5a2fde97421e8cbdc71ef19a8646869416ad13eabb9bcd56ca858a95086016b62e2248ed5f6e0484e194e46aec099980d18eabe8cbccd13c8dc740c3beade4359fd2d78069e45b4d536ec3754c740801ffa4ada70a475041d65e3c871da845d9bf25059334ad2e2415410058c378e4151f6016cdf50066519750809893c3385c3da315960c1130e2813102b0cace163980a7bbb32ec5ad94054480f3aaa0a08b3f6c20d8f08dc8cf5c0fb31baf8329644a0363e70d3c41d0f3d7ce4618eab4e16e8b2b522decf0046e43572d21210226ac54f3d963867c1ea2e062c6ae0e9797a7c7d7d19af45cfb7434f10f55acd7b12018be96e9805d68bec0eb3b85249bd14a8b8c11a3f7a12c387afe344432f4ee6ecc0f988105c8a8cc7c8fe2bc8cf18c5a68c93a86cbe816da5d761e7710ce9e62b71d0480d07e356e3504d2f26db46509a358472fb68b416b5a2ba6114e5c5ddc8b3f74542541bea0bf6c2c5c8750aac6737f958380dfa8b8b61b3453c7c8dcb11e9bb175bfd330914fcf0b533c3885d14de8eb6c2fd047dfc7bbe3f9eaeb5c6a3ad417850ec8f4b695e184ef44703795ebbae122eda69e2a29329baaccd90616484007555d8a9a9a0aa9465de8f1c3946e20873e226070707d8d8d860c3860df8e28b2f5e672aa48a95360d9691f1d422d4a5b7c4109562b7bf58dbdac3c1c985792edd339ef6ac53c898b6627ab60b7d43e63af35c1b3b189b9831cfa59bbdd1e7d3e7d2291eeaf3e86d0a249deaa12dc97383558fc923a73134760b43433770aab11be737e7e0728817ae44afc1d0c11bc8491c42c696b368d87d1b09695760e374103546b6e8b7d4c1814df938dc3c80c956921289ef69f58d447d4d1ff12de439ab521063bd09f53b86901c9ece782181a991e0ecd1e0dc752a7e62b2e3e49488faad458096153686f422d92b89186f3e04b978e163cf60f43babe16fc591f861930fbe0c77c08481264e5968e283085dfc797726fe732c1b1f3646a236c60771ba5a4850245ecd580e7db6da3849146dbcae063bdb3b18b03ef8e0037474743006ded0d090f159c78e1d7b3d601d3f7192a963cd48856cb09e4fad5860d1fb83c3c29995370aca2acc220b1af478de3e7e707673678a9af43aad4179787a33f38774344a1763d04512f418f4713a974877b1a1ab71288814b6d9ea45a782e812fb71322aece9bf8dfec337303c7815e7cb1b7179e3065cf375c6e5f47cecdb77039b570f626bc145d4777e00cfd0b3f0b5aec45e7d350cb83ba2afb81547db4671a07e0269aedbb06b432a2a6b8751b5bd05696601d8ba690035d9ad509452e62a31f0301aa46091cb285925ac358b83260133ccb518a52b6b989318b8185be3aa5b243a6db4f06b963fbe4ed646b122f1bbc44b09125f2644e0531116c06a7b7d4c6c0bc0d3837978da578c73db56a23821145ee626d092954629536e68c7d933e7f0affffaafa8af6f44484818525252b06ad52aecdfbffff580f5ddf7df2331712658bac6c65054557bee063eaa40f4d2dcca060181c1ccea9dc0a01066550ddd85d9d72f80d99a882ec5f721d7e9f1a8127120e3acbea1c7a74051d05c5cdd19b028549e5e3e4c7ae4068b42ca941b264f6277c719ec3978178387ae60bcff3cae6ecdc595f860dcf474c4d9d62134949f43f2fa23286db881eca2f760e9721c9b8d23d167a880832bd760b0761f8eb6137f957318e5243ded2c6a4265c3182abc43b13aa401ed657d7031716503c5fb6890deef222e8e64a348689091a5869c399a1247a1ab6e0a6b3d6dd4dac462d45e0e67fdccf17d92342a15e420cff7ec9410ad61294b886283a7038e9467e09bfe1df8ffded98fc7c77b7074df5eec6ced98dac668e7ce36e69c8b99595b101e1e8eb6b6b6d7d7e8b714b078f556f4ba878f2fbcbc7d19b0e8174fa1a26b0f295446c6a64ceaa370d1944b01a2f7d3d7d1793f7a9da637ba7a87be8eaa167d3fba4716bd8f1b2a611131a61f8b018b98f7fdbbc6d1d87c117d873fc250df699c6f3f88eb496bf15ea82d6e11151d1db985dc0d83d894751ecd7b3e4460f405d8d9ed411b1996f75be8a12fab14633b0731de3a8e949056b40747a0ae7118f51b33b1da32098de523488d489f32eebcb6c7d0c7d404f891aae7014b592d924af911e4b40585b1b510151645948b3f31efaec8d197c26fa9c618309187bb98e09cc75cce3e73193fb95492944098a531dad2d662a2f7c08cbd1b0e1c38c09c44337ed51aa25c21ccf5977936d605c1a293d01cb044c897a4676834556d5fa8663557f7c2ec51208563a9a505eeb2c242869d03150b2c5766f9d7e4f849f4350ea1bbfd289a77937438f40ec60e9cc18d827cdc581580db5ed678b7b8059dad5791b4fa308aaaaf23bff2162c5d4f20ce7c237af564d1e7eb8dfeb22e1cdb358af6ca23d8629b848eb41c3414d613e31d84dccd8368d8d20a6931b925af171421ea13ab68883075470893e78b0949622bf173a1ceb1d0575342be4b3c2e7868a0d54215372215d1acaacc3c8fa7d664ba7743692551a9e9539e1c3e7c185d5d5dd8b2652b626262990afc9ffffce7d7d7ddc00d962e1bace729862e65eae6796b567383e5c206eb387ac9e8aeb7be9fa8d639f48f7d82c3dd13b84854ebf6fa18a25816b81b14882363b75140542b2de32c9abbef2224f61c2cedfa506d608a7dc6aae823ea74b8e51026778d61f3aa0368760f45fbf61254fb042321bc1d9d9503247dd92db9f598a6310b6109acd3768196a834339fa82ca387da946118ebdac2cdca1c073d56a0c64a125782b571c252033ee2c2acc9685eda6676cc5c5778e1c2051c3a7408ab57af81bf5f2003d6ad5bb75e6307e92260bd704b0c0ff3814b018b0314375895b48e35760cfbcabbb0bfac0d7bbb4ea1b9eb038cf79dc4e49e717cb8250d77625cf0a1a735ae55b4a1b3f90a36c68fa2b886a856c50de2b58e22d4221b3d3ab2d8e7628b0325ad38b67b0cfbea8f23dd311b5d6b12d016b712710ed9e8a81dc3bac0f533cf0cc6537bcc72c81248a2158de0ab6402e165acc965536d0fd4a50e414d411b916edeb81eea8f166f737c10aa82611d4d88b327a1690a1424af17646ecfd38fc5950a699174f7eeddccf9b133b3b398f3475315fbddc0a2be87d70dfd67d7ade603eb79a0e2052c112eb0260958bd453bd1575081be5d8751d7f22ef9206f61a2a30fb7aa1a702f3e007742cc70373212e3a31f22674d3f523753af750f01916760667f1045fa36e8d197c7fe35c938dc7408c7778f228ba85bbd6330bad6af458679044a0bc65193d944529918bb20cac773df940001d05a4c06d12a96d012116760a125072feb5528deb01bcae4f34cf78cc637db12b12bc409efbbe920554e92018b164395850420cacf47ae138f45817e6b3658d3a9f0bffffbbfd1dcdccc9cb03c3169233c3d3d5feac870f17e2c2eb0e8861df381f5fcc5509925174379552b11b6c7aaaaae218a7504dd5bab7070db561c6ada8be6b6b7d137fa298e74ecc58596bdf83431027722cdf191bb15dede3d8e961da7b02e7e1ce50d379155780d164e471060b10d7bb4e5b1d7de127dc56d44b5c6d1db741c298edbd015e48f4a1b4fa4aced4377c5008cd48d97d47a4ce1e02760a90808c1575603eef2da8c87a2ea2348fcdaaa80326c08c9839e96067679adc2e5dc3538ee6f862b66fa5025c69faa953c014b848f9e7e9805161fbb4b827386556e8f457fbabbbb89c7da828dc949ccd44e4343c3eb30ef3f4d8125c2fea2e86e319c54b8d406bed9602d659299576f351b2c7aee6467328a6481751c5d19f918c94ac4686d3df6ec7b1b1d3d1fe278cf304e3475e0fe968df828d60177c990fe4e621a06066f23237a3f32b65c4263e787700f380153a25ab944b5ba75e5b07ff5468c340fe168e738366f1842839d1f5aedec11ef5f8b9ec63144b9473193ca8b81c57d52734102960c1911da88c9c25f897cd674d5341b0e71212914af27fecdd81d1e365eb819b102edabc2713ddc13359a0acc73148458802d67bf8603d74cc59a6ef4a31eaba9a909e19111f0f6f6c6a64d9b18257b4d8a9536ad58042c8e62f1725e9ba9ee85e7006bae8511bc8025c215c2c2c2d0d4d4444d3531ef474e614f5a360ea74661b2ba04030357d04446809383e770bcb61a1f95e4e3d3959eb81b61843f78bbe2f4e87554a60f62f5aaa3a8697b1f1b33de81b9c3247ccdf3d1ae2583bdb666e8275eebf8ee09ec6d388e54872d68b775c25adb4d68ad3d8292a47208f30b2f0c15bbad858f164949502824080c7a42a27091d28099843484a61e5f0e3d152b54a50d4251561999be1bf0e9466fec8d0ac17bf646d01714800c512ce1e5d31e8b03edf4a62033b78a9c9c9c444f4f0ff1579b101fbf9a18f9d578f4e8d1eb074b9b0d162fe7b599562a99e74a83cf0395f00ca84419b0242424514d3cd6d89133d89b9387b1044f9cac2cc0f0a18b686978076363efe34c75196e5656e1c1ba407cbcc2189f7a9ae1566933f6eebe8a0d917dd8b2fd0aaa77de8593e73198d9f42347cf169ddac4c8af49c1c8ce211c23aa954c4688f5d681c8318b4471de183acbf64353517b71b01868f898da9510090912aa028230139181adac1cc41925631b73723dd6270fb17e69b030b6c6e990d5680f34c1c5b5d168d15526202f87b480c00cf33e7bb7196eb06edebcc9a4c38c8c4c2612131399d1e26b32ef33c1a235ac17e9619fcbb42f6571042f869d05152b18b0aaea313e7916bd95cd188db6c6998a1c1cee3f858ee6f3181afb10e75b1a71b1ac085f6744e3fe0a3bdc0d32c627d131183f720fb9713d48d87096a4c33b58b9f602ccec26e063b6056d6ad2e8b2b7403f19699ee89a4047f5496cb4c94095850f1213f6a3af6904c18e218b7a2b4eca1220e65b98782971e29114c87533317958484a417a390b2c21f25c1ab2e22aa8cf3a022579756c0dcdc207f16ee84d5e83771d4c986abc9210ff33e797e69c6c7c762aa42dc9ad6dad28282cc29a847558b56a350607075f2d58b41f9a016be3b360f1d4c0f70223c1174f81dc6049b1c03a721a077a4f602cd808e777a463bc67147b775d44dfc0fb38df7d00a70bb3f0d5b68db81fed8c8fa20cf0b9bb2dde3e7016cd854711173984c2caf7505c7503b6ce47606c7500b9da66682746be372907936d6338d27904092b7ab1c33c08d17e4de8db3989c2754564a44715e42d1ec0e28708bf00511c21a80b8bc18a7c762a447d24e959ebb9c0a2ca15e3538028df64b8b904e35a58020ea545e36c843fa2e4c5a02828f04c0f3cc763d1b3d87383f58f7ffc036dadade47b4ec686c424e2b37c18057b3d8ac50d969efebc60cd55089d024b46fa959717e6828a06dde2928245cb0d078fddc66090292e1527e26847170607dec181ae777066f024cee425e10f3bb2f1d51a2f7cbec2009f789be26e5621fa0fde45524837366eba84a6ae0f111a7d0ac6b6e3f0334ec34e5509ec7677c540dd011cdf338996d2635869998d709b7cecaa9944f78e1ea82b68b05312df02e6fd2d062ce1e5fc1027b725c975690294343f1f44f9f89852022d2150bf25421e5797374065e628343534d1155e8c235176385a90877dc6eac4c0f3310a37771d6bd733fbbcefdfdf87baba06a6e4101414841d3b7630c0bdf2ca7b221b2cfae5d13d40a9715f3405ca4a73a995fc4b5d1c313f5814249179c0aac3f1e1718c1cbc8891c415b89c1d86534d0dc4bcdec2be9633383afa2e2eecc822be6a2bbe4d0bc417e1a6f830d410f703fd706ae20314afdb8fe8a8099437dec4d6a26bb0769c80b1e51ee46be8a35d471e3d3965384a4cfc64c738e2427be1699c8332e2b3061a0611ee12cea4261640fcf3c0f5164bb54850f32d4a82a6443106283ad213602e59e0893029b368e321e8a89b60fbfa3adc0a72c2787d1d2ed89291a4301f51be3914ab6cee4d41868787515b5b8b88159158bb6e2dd6af5f8fbffef5afafaf8e45bf3cbaa1ec6cb0381d0b4bad59bdf0d48d98e8a26a459beec4c5258879a7608de174593746ea1b707eb5352e3494e3d891eb18683b85b1e1ebb8da5483cb05e978b47505ee47d9e2e3283d3c70b3c48db641ecae3f8795fefb909e7309f51d77e01f7c0a06d6871162108f1655717486446098164c3b2751577c048e9635485d7f0883cd8751915209110131365c74e25860ded121531ee000c636f3f43e7ec6bcf333f789f18912151344b46f1e025c57222136031f84c6e1ec9e0e9cb137838118499ffc0b29d6cc13089c3a758a816b53c62644c74463cd9a35f8ecb3cf5e2d58d4b8732b1607ac97d1c3be905af134d94cc0e2f65773413513ac71dc48c8c4e1c97770324417576ab27072f83446f75fc048df655ce9e9c7b9ec55f8764732be5eed8a075186f8d4d7141fafdb8891897bc808e9444cdc3154b5dc42da967789899f8499590b8a5595d06aac89dec2569c203e6bb4f5306223061115da87038dc3e829ed85959e0d0bac65024ce7c3ecda16f78a1abee5d33528413654cc750a1a814b4c409c80230e17ab68ac0bcf43707034cef9a6e1d6c83e1c77778787ac306404f87906ebf3cf3f474b4b0b0ce8e964b4b5b172e54a1c3f7efc157bac4416581c45a06d2e0a6444c8db48508e1d2fe6ad78292fcc866a2eb04e12b0ee05910f6de42a8eaef5c0f5920462daf7e2e4d15b18d97d14e7462fe05256343e6e28c1f7495ef82acc18774375f085a7332e8f5e454df630c2fdfa9091fb36aa77de86abdf71e8590e235adb1f8d2aa2e85cb501a32d2338b26b0c4d2593880c1d415bc5080ed40d2133268b782841f22513452260318d80cb96cf280b70aae4cfc4721664fc243552c5921090818c101931ea782073cd4e787a7861c23f171f1feec5f1d010c4a8484056907f8e05abcf56dee9cfdffef637e2b1eac83f8919d4d5d5e1e8e888818181570bd686c45496628988cf0bd6bce505191956bce2a99ba580f54960342e17eec4a9965a5c4b72c4bb1d8d387bf60ec63b2671f4f0155cabda8a772bb7e3a72d7ef832d2129f44ebe2bebb05ee163710137f1b6bbc5b11b3ea042a896a6d48bb0423eb49581995a35c51022d3666d85fde8d63bb2719c072361d45d5f6311c203e6b775e274c342dd8e9909f40264ad29d08330f381b24eea9188eefe2d4b168c15546480d72c22a305277407ee21e383ad8612c6807ee0ff7e078cc4a6cd49486ac00ff1c271baf9af7244db4ffddc5c5058a8a8a505151616ebf16c5e28c0a2958f2bc82f58aca0bb3a19a064be419a8586049a2aa868235818f0256e06ed81a9cbd701be722b570ab390f97262ee344ff194cf49dc10da260e7b7aec50fd5497848d2e1c31526b8e767884fc22270faf83d62e27b10ec731059b9ef302b7a1cdd8f41c7ac1f096a56685413c79ee45c8cb48e62bc6d047b6bc750557804fb6b0fe240d5216413d512161061af1f148220bf24496fa2e05f26c40669d9b43a71a7403654d46f8909ca404ecc0c0aa29a305177425e52371c0858a3c165f862a40747e357235d4796a44281196976badcb06b4eb0dadbdbe1e5e5c54045c3dfdf1ffffbbffffbaae60a59e5066ae0e703eb651b765e8aa1f3971816016b8828967f24eebb04e2c2d0795cda1c850f4be3f0cebe1e9c3ff53e8e768ee0f2d8395cce08c7fdce663c4ef1c2c31063dc23007e4abebc5bbb06d0dbf51e625d1ab132ee282a9a6e212ee11c0c2cc7e1a8b71965f222d8e9e488beea03384252eb1031f37b6b47d05bd38fdebac3e8dcb60b5e563eece2e572c66b09f3cb9090260a264cc01260f92b5a89e75bc6068a8f19090a13c32fc62f0649615d28497b40414c0bd606dec85adb0e57774f8cfb6f2760edc364d44aa4ea29429aad589c3948eead22e7028bd6ae020303a1ababcbf82c5b5bdb173e4b186f9577762aa40b133860f1aa54cf9302970e95e89c50090909336055d2543834864f7cc3f1c0d90fefa717e2d2d8386e2698e0fdce1abc7dee039cee1ac189a173b85953802b0d65f8b920100fa31df079b40eee7958e34e6c1219417d8cedf17be0efb50f9b732fa3a0fc1a6c1c27a167da850d4a5aa8529342d7a6128c92d1e044db2486eafa71b0663ff6d51f425fd54154265742434987b54bcc5b34cd0943484096289902513059029818513111a69b81ae62a6214c6e8b115f2529ac0779496f28cb86434e541d1eb6ab911cbb03fe01e11875cfc457137d180f094522014b945d6e58ce56c085ea589ca6bfd0d050062a0d0d0d585b5be3faf5ebafba8e95ca8045bf340e582f7b14b894b6e385c09a09150b2c3aa5535553cf78ac4ffd22f0b9a31f1e7884e0dae5bbb8bada169f34a7e35df2d8c589b771bc7b0cd70647703e6b251e7516e1d13a2f7c176e8a3b213ab866e78ff7bb0ea3a7f35d4439d42166e511920e6f202cea34f4cc47e0aabd1a453202687675c1feaa5e4cec3a822102587ffd000ed40ea2af61187b8b7a901d9703293199a954c54fc011165480a8903a090d880aaa43444099dca70c1141354810959211a51be906405e26162a0a2b202b2483b8c01244f924219a007fcc692d1e1e1fc1a8a717a27514a70aa4b3c19acbbcd39fc78f1f33c5510a969a9a1a4c4d4d71f0e0c157ab589c5121072c5ac37a19d3362fda16b3b85ab1824ee954d5d4e2e4c8383ef38dc003075ffcd1c90f1f54b4e17a570b3ec970c69d9e365cbdf821ceeceec7b9c3e770357f2d3ee8df8b5fb2dcf04d842d3e8bd2c635576f5c5f998133a73ec2d6980ef878ec45c6d68bc8cabb02339b09e81befc43a79155492515947721e066b0730d9710c879a46d05f37885e62e207085c9d396d58179a04092171bcc54e8b74558fb0a01cc485e966b9a69010b382a4981da4249c2023e14da00a21504541416e0dd4e40349aa1347dec64370b6f0466a7239ced985e2c191098c39d9c3575d7e6a4a879fbd5d12f78e7e7381455b6556ac58c1ece540c1d2d222ea5b55f56ae70ae94a1de64b255f1cddba9117b05e65859dbbd78a17b0188f555d8b13c363b8ef1f8ecf1dfcf0b5933fbe0c88c2cd5b1fe1fd95c6b8bfab00d78f5ec4a591d338dd338a5b7bbb71a134074f3ad2f0688d2fbe8f30c3ed50138c5826e04e7b3fd3f5106a5785e8983114555d856fe031e89a0ec159330e3992fca8b1b24077e12e0c13a026da8f60b079948c0e87d0df7218072bfab0337b27e2fcd711332e365d6ee0239e4a807c06c2aa042a33c8483a434eca170ab26150948d82bc7c1c14e5d6434dc611da727a68ccbbc4b4cfd4161cc215074fdc1a3e8a932e56b09293989a84e6068bb307e97cde292b2b8b3953adaaaa2a63e0e9eae857b8fc8bab40ca05d6527cd5ab282ff0aa5653e5069a0a89623d0808c37d920abf720ac077aebeb8d3d64740a9c0837c1fdc39d0856b97eee0fcee1e5c26aaf576763c3e1e3f8c5f32dcf05d983dfe10ad8ba35e6b703a7c1bce9dbc83ec153be1e5bc0b19391790927509c696a3d0336a43a4ac16726585514fbccfdeb25e0cd70c608cfaadd6090cb48ee11081acb7b01b4dd92d88f28a830419292e67778a32a505e2af44f8a52029a245d4ca8680e50f6579a256f209e4722d94c435e161b70e659b0f42494e06fb0b4ee11d57071cef1cc0655f3b688a8ab08bb1ac01c04cb03ae605abbebe1e6e6e6e8c62292b2b33d7fffef7bfbffa7203072c6adc79024b4a92152fb1182abc44a83860d151e169e2a3be245ff6e74e3ef8d22100dfbaf8e3ebb058dcfde01e3e5c65802ff794e0e6f177f036f9cf3fdb3384f777efc4f9ba723ced48c18fab82f038c20aefc579a2ca340f771abbb18f78ad60cb0aac881a4151f9bbf0f29b8481c9102c7512b1565c18b98a92688c5a87eeb21e0c54eec768dd10469bc6304cc03a543580eebc5da8cb6a41844b24a4e80e800c08acbe77415a0ce51784b8a03c512d6b02d40a28282442437105e4052590b1aa176bc332e1e1e9834371fbf04eb83ff61737e364a03d799c6f6a3b49413e7eae3a5639bbbb616eb068bb0cf559142caa5854bdbef9e69b575c6e1079162c5ef60a7dd923c1a5a4c0d98a45c17a18148e2f9cbd493aa43e2b008fdcfcf1f1eefdf8b0b5100f4b4270ef400fdebb7417177777e19de153783b6b15ee1d1fc76f19eef821d2037f8873c0eec832f4fb6dc3dbc76f2227ba1d9e0e3b919e7d1a499bcec3d46a043aa69d705776c15a317e64abc8a03e740d766def20eab517fd3b7a70a8b41703a5fbd15fd0898edc36546d6e44a87d2064f88598b58542eca0fdee2244bda4c5b4a1ac100b25c514682978404b5a1df564446a65ec8cc4a4621c77db8c8f4a72b12b26157bbdac21cecf37b523a0107bd29b17c5a2fb3950b068f59d8265616181b367cfbe4ab0a6cd3b2f60cd778e1be684e54b9d0f5c1258c2ec986dde25d9604de09b90487c4d4cf87da25a5f39f8e3918b1f1e86c6e0e3db1fe10fab8cf0f5de52dc3e761957878fe05cd77edc245eeb6c4d257ed99b8b9fd684e3c768175cce48c1068b7a7c50528fbedef710625982f0b041e4965c865ff024f4cd06a16b54056f5903c48af221595602dbed5cd0b83e0fad051dd85dd28d3dc57bb0777b2776e7ef4673de6e94a554c1cfc4194a02829062ba1a96418c816b19e4c40da0a2980855e514684a1bc1d934023b4bce434a581c9525633867e68d87870e60978b2772ec4d985a18054b6019df0cb0e8628a853cd65ffef297a99121f55974eeb0b3b3f35575374c1748e91748f74fe09cefe645d5ea79a0127901b04e11b0be0d8dc0376e3e241d7a3326fe5ba720fce0416eb7f5e0e38e327c5f18804f0eecc1cd8b77f04ee72e5c1a98c095dc54dc3d790e7fdee2891f5686e1f37591a84fdd8766e77c5c1f3e8bfcb5dd70b5ae4762f231646c390f1bfb61e8991e84b161015c65751120c28f18517e2428c820dbd21115516968ccaa474bc12e125d04ac5da8c96a467ad006981133ae2cc00f593edafbbe1ca2249529c9ba4345693374d4d64251441eabc21b909bd804552505f4e69fc5651b6bdc1c3e8609171b786bab4e4d700b32d341cbe7585738f7aec9b4078bf6bcd3dd6728583425e6e7e7bfbab6196e8f45777ce19cefe67554d81753ab8552e05c603d8a5881ef3d88b7224af599a30fbe2646fe912bf15b44c9ee7ff829eeaf36c1a3ee227c387e12ef8d9dc0a5f60e5c3f3080b3a545783254835fd693d7c7479011631dc29d7a7123ad006343d711615d047fafbdc8de7606b1ab8fc2d46208ba26bd303228818382039c8485e025c80f1fa1e508111745bca61e363b07607b44228a6236614bd07a44983ac2847c2e6a027c90e3a7602d8394900201680d5494b360a81e0e256109e4a54e22d63f115e3ebe185ad58d4b411ee86bd88f6b2b3ca021263235caa43d5d0233b6e3ae5c50b1e8cff6eddb99aa3b058b1a785a8278dea6bf85cffec54c42a74e8dc4e6074b7a1a2c29a9171e052ea6569cebbc80c52a3750b0c6f12872057ef4f6c7b7aefe44b57c19e5face39103f7afae38bba9df87ca0038fb7bae341ef2ebc7ffe36deebda8d8b3d03b85abc05b78e9dc25fb607e1d1fa247c969e8e9d5567906d51850fdafb50993b0837e31244471ec296dc73080a198189d900f48cf7919162034cb537c05cce0a66c4ab5a0a2c872d094702908b88105c8807b4a3a74526f0e912b5522550c9f0d12e5201a812b55253ce84866a260c95dda02fa783e6e2ebb03172c486e45c1c75dd8c4fcbf251b9320ba7a23d9914ba8cbd73b2383399bd8ce754487fe89e5974191867644821a35b1ebd54b098b37f252eae58ac3346c83c0b96a4242b5e41859d57a8a60aa4d52c8ff598fc07fee44b467854b5085c54b5fee81488c76e7ef8c13f180f6e7d802f521cf164770eee0d0ce1c6b10bb8d2d284f70e1dc6b9dc2c7c77bc0f7f4e8ac6d789d9b854db81b8f8339824a3b3f387dfc67adf0ab858546355fc0832b34f22387808a66607a06778003a469dd035a88791ce66182979c24044117afcfc302000191290f40964dac474ab91902770d03e77650963682a6f84b24a1e0cb492a12b63086793107454dd80aca43c4a8afa71c12610df8e0ea1c2c60be5be0e4c9320a705478a4f60ce7eac854e2070f2e4c9674686f7eedd7b15a930650a2ca159607160a2d7e74c815c602d75b79817292fcc990a6b095823e3f8213a123ff907e2895720be2723423a6ff8851349872ec178e2e9873fe616e2ab739378946289873dcdb873ea2a6ef6eec3a5b60e5cadafc2d58387f197ba78fc90928f4ff2aa71b0f72ac2ac0fe256461efaf75d44b8e536b8dad66375dc0832328e212a6a0476f6076064dc0b3d03a25e060430fd26e8e91158d4561058cca0292247d29f1094f805a04046868a42b25097b585a6ea7aa8abe4428d806561b081a439929a028ad05c380971f27775565ec0354777bc3b388983ae8e0834d066f7d5d3d1204da5fc3c35fa71ff7cf5d55708080860e60b693aa4533bcfbb9fc3a21e8baa16072c7a3ab9d960bdcaf9c0e7292fcc3b2a2460fdb4321a4f03fcf194a8d68f9ec1f8a35b204987fef886a4c39fdc02c8fd01f8e2c2db78981f8d9f5b36e2fe9e3df8e0c4bbb8d1528777f70fe1e296247c79f624fe92493c596623ae360fa2b0ee2e726dea70b3b41eed354710609c0967eb2a4485ef4772d22836268e2022bc1fce4e3d3035ee869e7e27b4f576415bb709da3a55d0d3da0a3d8d75d0558b85ae2a09f575d052cf22e92f1feacadba1af950d1bc328a8098923296910c529edd0d65043ffd693b8131e8caeea4ebc1b1b00153111f6e623cb214dd48a7aac65cbe7df1464ae1f5a10a593d13a3a3a0c587484f8bccbee791815b2ca0d8c6211b068a981d7f3dbbccea99bc553e1389ec693541812809f896afde41382471ec1f89298e82f9d7df0a34b107ef2f2c5f709ebf1d5ddf7f1fd7a233cde5b898f874fe2f6a14378a7ae12efede9c2db359578babf083f6716e2d68e833839780df18937b0dbb310576adab1b37214c1a69be164980f6f9766ac08dd8384557d589f308095b1fbe1ebb30736d61dc4d8b7912fb0159ada2dd0d46a20510d6dcd0a686a9441437d07d4d58aa1adb6157666593057f784a698347273df457a5c01bc3dbd31b8b21b5fe467615b54124eaf0e648aab9cad2795f88499a5626fcdb9dbccc2e7d2a15b4752a5e21878ba0bcdab038b7ee142a25051537f6eb09e2705be681ae406eb1451ac27ab56e0d7307ffc1ce88f5ffc43f0d43b94a4c4103c20e9f03b67a2646e41f8d9d7075ff61cc0375da5f8a528100f3b484a3cf60eeeb4d5e3725b3bde2dc8c6471393f8736904becedd8b2bede73172e45304875f41b777212e1596a3a7ed18d612d0dc759261af970547b342783ad522c8a705d161bbb072c51e848776c2d363276c2c9b0864f5d0d3ae838e660d894a12c530d02d80bd652edcad52a02f690c4b7533ec28ba83d88075888a4dc0f1804a7cd3d982742b3734067bb0a785de62564babf389cc58cb386dde772fda67555959096767e729b0e85ef0cf7382019eba1b389577bab12d07acc54ef6bde49a15b3ea86f75ef6a5a7c231fc18178e9fc37cf04b883f7e0d0c2410859094188eaf9d83f13581eb896b209e7a05e0495818bebef711be4fb4c5cf1d5bf159672f6e8f1ec38d8a025ce93d88ab9bd6e2e1c951fc39370eb78bc771eee0fb1838fe10c111d750e3d580f36b36e248fb2076960f2225a414c1e68970d35c0d7b8d04d8e9a6c2c134075e0e6508f4a94344602342fc9be0e7590f0fe74ab83995c1dda9185ecef970b7cb808d36494dc48779dac4a2b1fa1378daf96103f17897bd32f1c9be1e9459d920c6c6647ad5f3723a0810e17931c5ec1fba9511ed20e580450d3c65e1158095fa0c582f1d2a062c11088b8abe74b0a6ca0d23a3f87e25f156611ef839d41bbf0611b80282f1d4270c8fdcc3f115f159dfb9f8e129498fbf12d5faa6a818df5c3a8ea7a93678dc5a8d8f874ee11e4985ef94e4e1bd8e76bc5fb4058f079bf1cbf61c5c2ebb8093a37fc0d0e96fb036f32344bb1ec5feb0024cc4acc7c496220c94b6a2b3b80b95e98d480dcb43a4d56a786846c0412d06b6da0970304a81ab55363c6cb7c29d84abd56672df0658ab87c148421fd6f22a080caa4177e34730d536c396dc56dc745f85638dad1808f58496dc747f972a512b85e582f3ae2b5c0cacab57af92116df08c91213d45ca4b5e4c91c6ae63b1cc3bddc268de51e073eebbc06bbbf1f340c5512c3a097d8a28d637f15e7814e68827a1eef839c487a856004989a1c45b45e05bd7103c24a3c427c4c4ffe21142eef7c397a7cfe2fbea8df897aa78fcb1be011f4f9cc147b525b85c5d83f776e4e3a38632fcb8af143f9554e04cc5bb181fb98f8973dfa1a1ef6b44acbb077fdfab488f3886aa982eb4c594a16b4526f6c624634f7c325ad7a4a36255163607a722da261a9e9a3e705474878d823b01c919563296b090d082938a3e5cb54c909c7f135d9557a026258faad243f8cc3f0a3bf34b7032211c42fcd37bc81bf18b33aa35ff8e7e0b7bac9f7ffe991919d25d7aa86a191919a1b7b7f7f582b5145ff57ce5059117826a0aac6a16585fc5bbe0bb08333c0eb5c3931077fc12ec8bdf0202894245e2078f083c2406fe5b970046b57ef3f5c30f715178f8c98778b2c10abfb614e041eb1e7c32388edbf99b71a9b51def6d4dc4a7cd1578dc5d8e276595b858f91efabb3ec1d1f38f70f8c277e83bf504a5bb1f20b9e013accafc0c31e90fb032ed3e1292dec7c6f863d81ad986f2887cd48427a32c281e79be5148f70c438a473836b805638d833f02748c11e4bf0dd9159fa1a3e818240585b0abf438fe181783dc5589185e1b36b58c4c80f82b2b2129664fad67c1aae42915d24514313131d0d7d767c0a2a507baecfe9598774e81941baca58c009734c9fc9252e0cc0e52da8f3586cf57bae2ab70437c1b668a1f085c4f83ddf15ba01f7e23aaf5b377041ebb1143eee28fc7eebef8d53384dcef816faacbf1eda941fc9a4e94aea60c0f7a47f187ce3db8b1792d2eb677e2fd2d71b85f958b6ffa76e1696521eed49c447fe53dec6e7b8043938f307eee2926df7e8ab1b77fc2d8a59f307ce909062ffe8ade53bfa266fff7d854791f1b0a3e4552ce87e41ff9145647ecc5fad01a6c08cac3bac04c24c475c377e5655454dd455be100648965d85778128f331290ea19849a9880a9cd71a5f90461212839cf39a12b793e756f4e4e0eacacaca69afee2e3e35fd15ca1c84cb05e5669614ea85ec248702ef37e7264027f8876c1e74186f82ad410df851913b86cf19428d7af4101f8cd8fa444cf487ce3124c54cb073fbbfbe34fde640419e28a2fcf9dc6a39a54fca938068f8b77e0c1c0043e27a9f1bda4389c6d6ec5ade2347c92b31a0ff675e3f1ee063caa6bc19dea494c6ebf887d39d7d09e791d4d69efa13ae52aca365e4349f275146fba89d2fc0f51dbf805da7abfc39eb1c7d87bfc293ac69f20b7e94bc46ebe859084eb084e7817159d5fa06ecb39146daa86a69a0a0e654de071ee7a643ab9608d8fc3d4e6b8fa02a23020b16cf97c271bdfcdd3ea1bbacb1f3d7913072cba8875a9fb392cba60955320a55f30dd748d8e085f965acdb567e8cb542b967997624e79727278149fc5b8e1b340637c1e6880af430c88df32c64fa1f6041e4f921283f0ab4f247e24aaf5d0d91f8f5cbdf19b7b2001ce034f56fae3cb7bb7f1649327fe5c958cc7c4db3ce81fc5e7e555b89e1081d3254578afa901f73257e2e3edc47bb575e1f3de01fcf1c020bed93f8caffb26f065ff51dcef3b8d0ffbcfe383031771a3f712ae75bf8dcbed9771b2e6320ee69d4373d2496c5f731a055b6fa0a9eb2bec1dfb0ebd47bf4349c56d74669dc0dae075b0b2b6c6e09a6e7c51b80e3b7cdd6063a0cd755a3a5928f00bccbf3f168f608d8e8e32cbc138069eaa175d8afff25a93895a51b0385ff87c60bd8c14c89876e197ab568c62b1c13a71f8303e5de582cfc2ccf0598011ee07e8e3ab105d7c1f6a4254cb91a8963793129f7aadc0f7ae61042e6ffce4ee837fa17e2bd011df6e598f8777dfc3d375d6f85345367edc924fd2e2201e9454e1767c184eae8bc1b98616dcdad986bb3b4a712bbf0cef1634e342f13e9caa3a8c93cd6771aceb1afe5f7be70155e5b52dea44504410101151912216449a4811a4f726d2114114a98a8820a828288a3596686c892dd6a8316a125ba231b1448d8d622f494c4caca8c939b9ef9e71c61b777c6fad8d1bd1080262ca7d678d31c7ffeff6ef0dfbdbb3acb5e69c3bd69e66c7e632857cb8b99c2d1bcbd9b0e932eb365e61ddba8b7cf0de29762f3ec4ea91dbc874594d48af65147a2e65dbda0a3c4c7be3e91bc8e641a51c1e1bcd82081fda69b6a92e8c2b000ad330a0cd9bf594e39eff72e75d8ef2f2727c7c7c146049ad2537fd7df4d147af611eebc9928eac8dd518b05e654b4c7340f5d414cee0e3cd5b3833d44598c39e022e0b1155990bb8ba09b84c8449b4e46eb0230f02bc79e8132acc609800cb9f1b2e6edcf370e7a1a78820836db83ea7901b0777519560c7fd4902aecc315c59f21e5726cea03c3686fd018eec8cf667f7b85c3e9fb380834b567348c87e71be7b7c1e3b87c7f061b00b9b9c7bb2a14f17de37d76775af0eacb43164a59b15ebe306b14d98d1756bbf61c9eacb0ac8be5c774240758e456f7d4eb7562d898c1bcec7fe63d938cc8765837d155b9915f088483046b3b300ec8d3a7cace23ab3749e1fbffdf69b622e4b19194a47beb1bd76ead9dd501d15d65e2b7c19587f1587fd453ed6ae2d02ac9c08ce25f4a322b2271784a63aefdb9d0bbe267c1b64ca4f4293dd0b71a64af85b55c224de729151a2273707b8f0d043682e6fe18b855872e5bdf95cdbb1967be23a5505a3b89d94c677a573b8327501971246723a208003ce7df8c4de885d361dd96561c0273dbbb2bb7b0ff69bd9b0a7a7359ff6e8c9ce6e267c68d4918d5d7458db518be57aeaccd769c50c6d15e618b7656dd260b6ac3dc8b28d3759238280c94161e8bea9ca888c491cf4496276881def0cf67c92ea25fd2b0d42dae8296a6dd509d6bc450dce70969d2a2450b29e83844b3af4cde863a53c054b7c4912ace723c2e6804afd3582559d622fc0127ec3b951419465fa732ec9898a28610e837a51e163c645bfae2252ecc6ed102b1e0867bd4afa5b1e11fcd03f4c448903b8e5e6cc234f0f1efb3a702fb41717372de3ea87aba98a13b7f3d2b81b3f941f468ee26a490957b28bb81c93cdf9c014ca3d1239eb11cf29cf411c7773e798431f8ef6e9c1b1ee667c29beb4fd263dd86e62c6fb46dd78a77317e6eae9334da72db91a2d19a5d182c2ce6abced62c15bcebd08d254414300346ee27b9c0c88649cb339f362dd6a768c7ab7e940ffd6da75b73c5180f57683c1921d5865cabd9c7d977e966ce4d4987a0e0d880aab67de951aab216035ca043e3319aade6c50b56ad5fa6996ceb46a53583e2a90b23437cea5fb5036acbf70e67b511920c4db944bfe5db81962c2dd506baa023d7824fcad7b6e117cd73f48448acedcf670e0b1a7ab80ae2ff70799707ed3522e7dba957b71fdb93f3281fbc392c56ba3b89914cfb58c242e8d18c2f9c1a15c0874e592a32d17adfb72dec69e73d6fd38646dc79601de2c70f526cfca96e1a626c419e813a4d30e5ff1ff0bd5d0244a4d8d30355522046021022a7395ea329133671da032c497741b33a64438d6c03342cb946eaa6acd0696dcef2ebbafca792cd9ed5eae1f3664aaa261c914cf99425974edf9048957814afd356babda09ab1f6fde4a79ba3f15296e94a7ba7136cd870a0157a5308b15feb2c85a572efb77e2875053e14ff51526d143e16fdd76891060f9f29dab3df74468ff8bb7308d01d63c18d495ca1553283ff225b785bff3303e847b9923b81f1bcf5def607e76f5e5c7011efce8e6c9b71e1e5cebefce19219f440f657e7402a5fdcc59d6538d0f2d54d8dd5b951d3d545863a242b1be2a81eaaa58b76c89632b359cd55a61db521563e14be9696bb178ee192e073a30ccc2982c3fcb9a89d1e2f6bdd0ada3ce6963a71be4d8bb77af6207a9cc8e96a6d0dede5e51bebb79d70a9f4c3728c16a4a71ffe7c1526b53b76ff53ac0da2134d6e9d400ca920728c092722edd9bf2e18e0ab8cafcbb51e663c0e5a08edc9270091fe67180f0adbc06f2934b24dfb9f870d34338eddef6022e611a032da90ad3e7f294048e9f3bc7b725b9540d72e56174300f86c40a4d16c5dd81c257f3f2e3aa97075f6564b3b6a098251ed6ecb76d49797f152afbaa70aa572b3eb73665a797379bddfd586a6cca5cad560c6fa3828dd052e6aa2af412d25980e5e2348079f927b932d096145b53621c8c14e068b56829cca8b9a2dc517381251b09488d25cda1044b4e3934a620db4bc1aa3d8ff522b05e455bbd6ea86a27acee141aeb587200df0c73a36c843b15a91e9449c0327ca91821e08ae8c5395f53cabdf5b916a8cf4f02aeaa907e3cf6f714cefb207e7689168ebc173f785a09475e40e7234c63401faa22daf17db22dc73efb94935f1ce17a61363f4778722bd099efc3fd299b389edd2b3fe0fdc4483e7754e7b2b70adfbbb6e48a5d2b4e0ff2e3e85b1fb06f4d259f6cbacefe5ddf73ecf36fd9b36807d9b6eef8a8a862a1a24257a18964bdf791d9535990be878a582bd21c8d7137ab5e7c3653d5a044af7bcd1ef717825534b55160c9ad3272ca4119199a9b9b2bb6d4bc964c685969a6b9fcaad76d0295a221fc15e963eddabc8d63095e1c1be2ccc9646fcea47a5396ee29440096e54b658a84ab07677d8c28f3d6e36a70076e0f34e541888d80cb9b2a255c6ededcf2b6e69eaf2d8ffd04787e363c0ad7e5a7086d2a27867368ef2ebe3c7395c35f5f66ff8193ec2a9dc2014f03aefaa8f273402b7ef668c535176d4ee6c85d0ffb593ee7386b375f67f5d63bacd8fa88551fff93ad871e73fcc41d9694aec2b2734fb484a9d3d7ebc4ca0f2eb03a791b5fc7f560f400632c3a489fea0ddcd5f5c8d1e9faa4bb6a5d60153fd3afb021436efa539a4259284476ae6826b0526b4ca1fc926a83d5d4d9f53f525b294da19cc7dab9690b27225c381ed597af87ba7122d587d3997e948df4e15c9617e5390154a639521169c659dfce9cf5d6e54a902eb743bbf230d856a1b91e780ce48e73143fbafbf0b3af25f7fd2d791460c32f7ed63c0aeb4c55641b7e0ad3e072b801e7a28469f5d7e6869f002aac25f782db70cfbb0d377c0df86c6429eb5256f2f982ad6c78b79ce269df3121e334d3461e656ac145f28a6e5330e7015b3e7bc4b1c337797ff94ef6ecf89aa205dfb177d47b7c3ab023e33d8de8a4a1aad05209dac60c6eab57672d79e5b699798d986e9063faf4e98ac951e9c02b37fd35b49e43036a903e3585b220c88bc07a99b66a4ce269f39b422d451923598efb648013a7050c27a31c3991e4c189f4204e670553363a80f2d1426be5065139d285f268334ef976e2b497b6804b47c0654855b0158ffc8543ef1eca5de718a17902f929c0967b01122e4b7e0db0e271700f1e46e8713f5a8d7b512db92be47e785baa82f4c4f3da7133c890e343c6722a29976fa696b0f2ad13cc4e58cf21f17f3e9397cffe499b59537094c5f995cc2ffd8171a5b71835fd7b166dbe43e9aa9b246796f3cdc809acf6d261aaaf095a2dabe7b026eaf6c45343ab7e8dd5481f4b0ed91dac5fbf7e8a3df072cac1c1c1811b376e34ff5aa112aca640a5d8375f4f6b92d7a1ad6a978adcb9e103be76771291596fce0a0d733ad249f85bde9cc80ce24c7628653902aab1019ccf0fe6c26857ca63cd382d34d7292f2d2e05b6e587904e225a143e95bfabd05cc1dc75117009c7fe4ea0a3d046bd7918d8875f837af36bb0058f827bf228c49447a146e25c68b2808efc1c68cc8d41e1fc981843c5b864d64cfb8c8db1e3b99213ce57b397b172ee61c6e69d2363f415c68ebe4441ee2d268cbac9ac99f72898fa03a3275c635ae2e75ccb8a63baa31673fdba2ab271e4f2cde28e56d8a8b5f95dab93da60154d16fe5923c192f51ca403afdc4223c192d16233f9584fa71b9a02d69fe55bd55e2b9451a1acf37eb0bf03c7accc39ed642522403bce450ce0f4705f4e6585726a6c046773c3a9cc0fe5e2f850ce8ff5e2dce0ee9cf033e0a48726957e9a7c1faccfed905e3c0870e6be5700775c23b9e715c5bd204f1101f6e141702f1e0599f338c84c1c4d046c46022a43ee061b7367a0335589615ccf0ae083e2edec48c8e4dab8204e2e7a878d8b0e32bdf404efa696b0312a8cf991b1bc973c96695173294838415ed265f2a38eb161d00a2a474433c9bd130b038caa2b2b0b1f6b63e7be18abb6fadde2736db0264f9ad2a03defb5c72fbffca2d8e5200b8548b0e46e52d9a0bcd9d70a65dd86c6ec0a7dd1ced03f1cac27c9143b04581fdbf7e3f35e3d3962d59bd38ed6220274a022dc9573c3fdf966f4204ee5c7716e7c1417260ca2b2701097f203291bd68793819df9da4d8bb35e6d44c4d88edb029abb017db9ebe5cd1df730ee7bc70853194a559803f7c37a51156a269c7e29c63c1828000b77e17142047732fbb3b770051f0dcfe7c7c2602ae74e62cbec9dec5dbb9fcfd287f0fef030063979e0d83b049fde61acc8ca6343f11ce6252c6456c8bb2c8f5dce111f4b92c40f6499004bd1b2f78d16ec32b253740cabafd978e1a4c62de928eb39c8bd58d2144ab8a4e4e7e737af29546a2c25584dd1547f34544f7790cee443e1636db0b3e7a36eddd967de8bc3c2293de1d08f324f272a0789c830c99fd36384d62a4ca062f250ce17c571b1388c4b9384994cefcbc95003be72d716e6b435177d35f83ec0803bfe7db8ebe3c41d2f7feefb46f230643055e10385132fcc65a43d55514e3c8e0c1650455395e1c0c9dc6c36a52ee4664938d766a4b0bb78294767cde1cbf418f67a1a32c1a63345396b98b3e83a9189e788b418c392ee866c0ab4677dda687679f9b0cfd980d8fef6acf53555643e6bb75065bfb1bd6211bade2ef60d4ca6787e4890e41c9632ed3e2c2cac41f51cea5dd2a9edbcd7d6588d75d6ff4cb09413a412ace5d67d5967642ae032636fafde7c6565cb49e1379479b9723ecc938ac440cac74451367918e5d393393f3d910b25d15c280aa322db9593d1267ce1a1c357fd5b735a0076d5a73d3ffa9b8a08d1963b3eeedc0d0aa26a500cbf44c5f36bcc507e894de4d784381ea70ee05aa6379b47ade7f29438be9d379233530a389e9eca1171ffe1fe6d3917da9eb3931239f9e1418e7f70846b5f7dc3f64feeb364dad7fc5452c8753f67be15a6e85c5112a5814e2cf3eca98802f555d4d86f6a479be73a5dd46e2050bd1fabb8ceaac9f58d458b16d5d473900e7c43eb3934b8da4c63c07a1e2ab55a853cfe788da5ad986ed82ac09a6365c322f10f5a6b64c436a1b9f6f6eac3619bbe7cd3df99326f0f2e84fa71312184f3d9719c2f4ea662461ae7678ee042e9102e4c8da07c7cb070f82d39e4d79efdfddbf095b31ae59e1adc107ed88ffe3df929c896db614e3c88f1e6617c100f87f9f130a52fb732acd899b58cb339097cbf3c8f8bd3923933348aa389167ce1a6c3cd103d7e2888e25c7c02df05daf26050377e8db7e1d794506e2c5ecb95ca9f39fc4525078b8b581c1f4b492f6d663b5b28a0315251e733d3beb47eb3eeded3356035d279974336717a3e6be7dcb973cd34f3ae5e3d8f25d3eb9b9271f36769abda3e96046b92a515a59d3ab3d0b00bab8c0dd96a66c25e0b738e88c8e794b32b15fe7e5c0a0fe67242389745f475a12899ca59a3393f378b4bb30560d3e2292f0ee794881abf1ad4957d03dab2cf419d632eada8f0d6e6865c6b143ed54fd1ddb81d6fc6ed6146fc9466c5d18c3cce6485f3ed92542ecf4ae762b21ba7a36dd91fd08ef34e6d795812c39994b1547876e45684310f93ad793cc2865fb2edf86da43b7732e2b9b27c015f7ebc9d3c4b6356da193043fc0864fb945e2d3539606aabc87caedf14367c3f56ed21a7179eafe7207798bef204696d1fab216035d4fcfdd1607d209cf76c4b6bc609b0a608b0e61b19b2d2c498cd3dbab1dbb23747ec4594e8e6ce858020ae4646702d3186ab9943b8583882cad963a89c9fcb8579a3151aac626a2c670b823836cc867d3efaecb06fcd3efb561c1fa0ce457f5dae871bf0ed10436e08402ea6f97025cb919b3342b93a631857d3fb713dd68e834186e23d5bf3387d0097679670c0b6373f240770eb9d591c1d3e84ab71fdb835d48107239cf8c7a4086e789b51d14797f93a1adc0de9ce3b6f952aaaf759b7d2e68b6ed602ac375e0b58fffef7bf090d0dad59339433f0724ffc2bfb58b5f763bd0cac1741f56799c067c12a65cbc66d8ce86345aa4167c68ae8a6d8ac1bf37af7e23dab3e6cb1eecd6e5b0b0e3bda51e6e1c685a010ae46c5722d2989eb19c3b93a2e854bd3b338bf601c958b84cc1f4565e950ca8a223931c69bfdd1ddf9c8b91ddb6d5ab3df499d93fe5a5c88d1e3dab02edcc834e3db3c07ae15f87323cd869f132c44b060c3675e3a5c7533e0d1c24cf6050ce6487400370f1e6473d1264ecd9ccaa17d27397af41a951f7fc20f5312b9ff760e15112250f011da6c562a4b366c51e4123ab4d6e168775b4559c916afc1799743d66fb0b4b4ac49ae484f4f6f061fab1658320bbaa12d74ff0a5029c19a3a6d3a9b85c68a36b72052af2389069d182954fb84ee66ccb2b26485a33ddb06f4678f9b3347dd9d38e7e5c2e5a040ae464bb8467023239d1b39195c9b3c9a0b7372a95c3c9e8ac50554cccb124e7e0267c60fe2ab9401ec0a34668b7d5b763a680a3f4c8fb331469c1fde834b29565c1fde937b49bdb935b83fc7fc4410e0a4cde32c2fbe9d9ccd72734b6eee3bc8eebd97d91f12c2d6759fb3e3d04f7c76e9ff5056f56fca6eff1747f61fe6fc814ff975cf1c7ebd7b9d9c29f3143e969bba2ea7ccad152d7f5f1a1536c1c79263dab4698aee60cac8b021f51c5e9a57283596469bb60a53d858b0ea33817fac292c6593002b404482dedaed08d2ed4044470386763562548f9ec2f7b2647e7f07d60b077e77680047c2023933d097ca307fae4745f06d6292d03699dc1893cdf5fc315c9d92c3a5b902acc51339b7780265734673ba680847c604b13fde814f7c4560e0de95a301dd391b61c19558e1d42738f330c9876b91761cf2e9c4190f437e9e18c636ef817c32268f4b6517583a6d3fbb3cec59f2d6876cdd7f833d87ae7160df794e963fe66cc57df6afdec9bae2e56cf8e2123d6c062826487d353a70a1775fdaabd4dd73baa6e5893085f7ee351eac356bd628fc2c65456539fd201b4cbcf20e520d8dfac17a91a6fab37dabe7c1da289c778f1ee638b56d879b8e2efeed3b32d0a00b838dcd48ee69418e8d1d25ce0358eae7c3d688811c4a8ce3f4d0182ae3c30518e15c4f8ce7db11a9c2b4657363ec58ae8fcfe372712ee767e753b6b090d3f3c7f3cdf40cbece15d1de706f8e463bf3cda0fe5c8874e787383fa1ad82f829c98daf038c39e0de812b43ec291b16c224631b2eee3dc68963154c4dd9c0d2ee5d999b358b95ef1f67e79e0ad6ad2de3e8d5ffcbe53337b89597c55d1763f6f63446ab4575364e645b7dae8b48cd44d1fcb2c54bc16a8ac63a76ecd833a5ba1b52cfa16160098dd5ba0eb01a6b02ff68b894f35812acfedd7b62a5a98d9d80adbfb62e5eedf509e9dc9528939e24f5b625dbc195c99e7ebc15329055b1b17c9c3c8ca3d919948fcee0525a02d793e3f83625896ba9a95c1d399aab39e3b89c3f8e4b857954168bc8af742cc7a78ee2ebbc788ea7047326de978b837df82ede839b43edb9106dca97be861cf632e662d200d63b7a89f78aa0ecc049bedc7b8abc918718a9abc314bf304a0b56b371db69562c3dc6a7dffc37151326f05f2e76fcd3bf0f95411d09b5ec4a0b95168ceba0cf0f761638cac26bf52de934d17997a3aaaa0a7fffc09abd59b254b7ac4af3ca29f65263491064167443c17a997ff5473bef122c07b3eef4d4d0a48fa6267db57418208211bf8e9d0937ecc6901e16a459d993e3eca1c888991533847786a6b0217d247bf3c671bc6832159327702957f85a2387712d730457d2528464725140763e6b0c67c78ce254761a5f8f4ee0684614df24f9533ed8954b717da98c35e5487067bef0efcec9206b0e07b952d0c98a4fa6cfe1e8ce03ecde7c80f96f9733cc2e81707d3dc6fa0f63c1bc4f58b1e22bdedd7a8b23b397f3d0b937bf0ed2e2e1140796c58590a2abc96742c3fdecd08b58dd172f423fd5584d07eb7ffee77f8889895300a52cd53d79f2e45799c77a6a0a9f074bd91cf3af6a029f9f6ed8b4690b76666698b651a787f88c169a1ad86b69e126e00a12ce7cb471378609e73ecbce9102771fa685c6306f8888bed27358933d81cd0593d8377d26c7e7bf4dd98279544e2fe6d2f85c2e8e1d25c01a4165ca70ce258fe0d4b0644e2424702c762027a27d3915edc2d9682b8e47f5605f981dfbfc2df922c4910d960e8cee68cee1e5eff3f9d6cfd8fef67a3eddf2155923f7e0d5b9072146ddc88ecae79d25fb99b5e004dbb75fe0aa431faa06ea73677a2ce533b2a874eec655d7ce022c1b0a0db55fb883f4d999f7b79bdce032373757b1d34159372b2e2eaedea59d0683257d2c995358a3b1ea80eaaf64069fcebccf62e3a60fb0eed68d2eeaea746dad8e8990eee2dc4268b0beda3ab80993122422c5f81ebd18d9d78ef1ee9e4c0b09675ec2089665e6b22a77121b0aa7b36dda1cf6cd5fc291e56bf866d53a4ebfbb8a33f31770baa49853b9591c4f1bcad789311c191cc6e1587fbe8a76e38bd8feec8e71616fac1f1f78dab0d2c5965986b6e49adb73e8adc57cb27637db8ba6b27ef1477cb0ee084307bd234cb60ec1c65614662d64d69c3dbcbdfe3a07924671cbbd330f66a6717149013f4704f07db80e3fb87761559f364fda9dd40556f5ee86c6ec20ad3d649f687777f79ac8d0c9c9a9de7a0e2f2d63a4880a6b81a5347fb53556edf3bf12544fabcdcc10606da5a789191a2aaaa8bd59dd22b7b6c88c6235115969b56c4557019b9d881a232cac18ebe5cbcc98c12c491dc9aa31e3593f612a5ba7ce61c7ac85ec59b88c832bd67078f5460eafddc4e195eb38b468099f4f2f617f7e1e7b46a5b233259e6d89216c1f12ccf6c860a6db5991dfcb82d17ae6e40aadb57bea4c3e9a349fcd0bd7f2fe985cde9fbf95e58b0f13e298464ff17f0db6f4a0a4701dc5259ff3feda137c63db9ddbd9c102ac497c3fb9809ffc8db816d19e9dae6db1d252afc9337c1eacc993a636d914cab16ddbb6df95eaaeaf9e43e3c1d2f87d14d858b0fe8ca870d3c66d989874ab2959fdbb5ff6938c62658158450370e99fa8a96121208b7670644a6434cb33b35837ae90cd93a609c066b373e60276bfb584bd0b970a59ce1ea1cd3e99398f9dc553d9322687752386b35e44981b13e299ebeec620a32e848a683454db90a10666ac2d9ec70e6156366597b0bee41ddecdc866f55b1f307dca7e1c4d06d053041fd1aed14c9ab88ed205a7d832ba940ad76e5c9b379ef2b78bf94e44ac7267ea915043b20c356b5ac93d05eb4d4542c9ab82555959f9ccd28e9c30ddb56bd7abad153eafb15ed50cfe9160294b454a8d656262fabb5f74434545e54d74dab4a19f30a7993e3e2c1c9eccfb39b96cc8cb675341215b0a8bf850989bedc5256c2b2a66eb84f16cce19c3fab45456c50f66b6b717c1264658b4d3a28ff83ff651d7c159538f710363599437954d8101bc3f740cabc6cf62515a162be77f4876fa267a0a002dda762071e048f27337b270d919b67a87723ece85b2f9459c9e37994ba1fe54da1ab0c954178b962d157d0f55856656151ab8a5308fedb4db535c344d80d5741f4b9a3d0996b254b73cd69767d8a0bcc2d705d6ab02d690d72b9df78d9bb6bd12582dde78f369e752f1857510e6c5454036c2cd83e911912c4c1cca52e1b82f1f96c47bc393787768224b62a359101242e18001041a1bd359a30d1d35d43168d31afd566a7451d3c2ba9d1e5931e9cccc98c81a4f77de8dcb64694e21f352c6b06cde0ea283a7d355bd2d96ed8d488b9b485ece66de79fb209bfbf5e36c660427e71672747621474474fab1ad2d530d0d0968a78fb5be218ee636843afb90153490c53367892873d12b75a7975b6894a5baa5d4b766d8a01da44ab0644ea17a13a3c1e680abe9e5b8ab67de5f05acba44555c4fb7756bccf5dae3297ec53156968cb0eb4b9a9d1dc385b9083132a687883e355aaaa2a1d692b6ad5ba1258faaaab45151435b559d6e3a1d89f1896392d058efbab8b038369dc56963782bbd80853377e22dfcad2e2d5b63d9b11ba9830b193d7a330b67ece27d3b5b4ea48673624e31078a32f86cfa443e2a9cc0bafcf122d8c863595a328ba30358123790356fbfc3fcb79a36f3ae1ccb962d5338ed122a65da7d5d593b0d3685adeb01ebf9e6497f4d1febf580a5aca6a7d46632109019c9321068a9a2a2f07164bf67159516a80ab8d484a96a2d028456e2d84a3ea785aaa25daf818616de7d7d18133182a5f6f62c1070cd173eeeec51d3282dfa90fe169118a8b6c6ba630f92a30a1899b98e39a5bb78dbc99d4ffd1cf87aca38be289dc4ee4963f9282f9d6d39e96c1151ea0e61967797888873de8257328572ac5ebdfa9952ddd2cfba7efd7ad3ca18d59ec77a1d1aabb93453fd60554785c62626af09ac979851994dd3a2850230e9fbb454157e908aaa2240503e2efda276ea9a38f67621d3379c85b676cc152672cee0246665cf6672fe07f4ed164087966af4ee604c9c7f1aa9498b299eb287e9437299ddc7820d036cf8382e84bde9f17c9c9ec096d860e6b95831bc8729338b4b85296cfa74831c5f7ef9654d64a84caea8ab205b8332a19560c90c9dfa66d9d55eb3296cf2ee86923f17ac67205379224f60ab79ac457564aaada689b5993d29fdbd986bd38fd9516994c60c61c6e8598ccf5d8fad804b5f682e53cd0e043a84921451ccc8b4354c9cb49d0929b3c8f18d27c3de8be1d6ae2438069118944656ea02162c58a748ff7a15b06edfbefdbb52ddb367cf6e1a58b5a34285c66a266df5476e4d6e0ee7fd7568b0ba1e6ba3d69aee467d186aedc40c1b3ba64525333d3c96928c12268f5d8fb3551c066a1a7414d2a7536f829ca2890dc825317a2ea9c35630327d2d99196b4819b69cc111b309f51ac3f492f9d56de5ee371d2cb955263c3cfc9952dd1919198d3785cff858021ea9b19ac3bfaa2f59b5b961ab0d96f15f04ac8680a7264c9eb1416fe27ad930cd52c8c0044ac2a2281a9ac7d4823584fa4cc058cb98f622009080f56867443f137b5c2dbc71b70e121248ff5e5ed81839d0b3732f8a274f655e13f763d51ea3468d7a266bc7dbdb5bb196d870b06eddaa014bce0535c41436352aac0faee79fd7e445e8bf88296c305c326750448c86fa26849b9a53686e49b17f1c53c3229818994449ee3b640a1fab9f7938066ddaa1abd20a5d6122dbb7d2444f9853bdd69ae8b6d2a05d4b75f4b57599fc64a3dfab98423966ce9cf9ccd28eacedf0a26bbebc334503c07a1533f82260ea02ab3ee7be2ef06a8365f237d158353e976299491d3d9d2e781af620b75b2f26ba0552141ac5a480100a13739992b39461b16fe1629340375d0bf45bb7a39d6a2bda89485321aa6a18ea74a2a8a8b4c15593eb1b9b366daa71e0954d9c0e1f3edcc8a830a97a115a82a5a6dee685a65009567301f6aa5aeff9fb94606d5068acbf1158b5cca2aa30773aea7a58e91b92626842aea53d13fc0631597cc1e3030229189cc394acf9e4a72d6258f42c06f916e2e7321a6fc79104b88e252eb498f973965783f58a1aabacacec991af072ca414e4334bedaccb0a76029720a9bc1c76aaa36abeffebab459ede90693bf21584fe16a85869a1686da1df1efd8993401584e3f3726fa8432c1c79b02770ff282a2c81751647ec278260c9fca84e46914244d2127369705b31689c870d12b9b42b9b4230bddca6c1d0996ccde99316346d3a242852914e0bc08ac3f4353bd08b2ba1ed712916cf504e996bf2d58b523c956c28fd26ead4d0fe1378576d027a9ab2999364ee4b9f993efe14bbeeb00f29dedc875b225c75188bd9538dab0706a49a30bafd535468c1841efdebd6b2a2ae7e5e5357682b43a13ba3658756df0536aad3fcb24d625122c9909bd496a2cd3bf2f58358be10aedd552e1d8b7154e7a37cdb6786ab72752af13838dcd1966d18f51766e8c767427cb610059fd9cc9b07765de94194dced27991032ffbebc8651d3d3d3d05684d6a20500396ac32f33705ebef6c0a5facbdde54cce2b712ce796b21ede57aa5f89e64b2887bdbf678e9e8e1a5ab8f477b7d061874a14446850b16350b587bf6ec514486b2b940bb76ed888989694254f84463a9bf04ace781faab00f6bf112ce5faa4621d52019a8a42545aa82ab6f7b416e79aaaaab46fa9869edc45a1adc3e4a26216cc6f1eb0644b1439032f3596b6b636d1d1d18da9f37ebb26135af349211009567d5b665ea4bdfe6c6d56bda433bd7a1ecbd8e47f11580dd76e4f0baf491fab8ae61873e7ce55ecc9d2d2d27a61a7fb37ea5e17baf32c584f345643c07a19600d9d8d6f0e91d79d5c34e53f6009b0e6ce59c8c3878f9a052ca9f964e94809d6dab56b1b0e968c1e525232491a9af28c8fd518b05ea6b55ea6dd5e065b434cb08c10f3f2f2d9b861cbff3253d878b04a4b6735a8b655632ad1c8259e462de9fcf3b77f3236378fe1c2cfd26a5bbd5da6cd2b80d59cf222b0ea9bb41d3c78886265dfccacbb62ebcaff2f7029a728a41f545c345dfcc0c6bdb4e64263474949c90b9b37bd515ffdc9e2e262068646d2d5c80c834e5d15a522b575daa3a5ad8bb6563bf1816b8bae62ebb23c6ad71c9fbb4febd9e7cbdb5ab56fd7bcf659d1d2fafd7dcad7d69667debbe6353a787878111414428fee3dd0d5d555a86ff92b9622130d94a2b84f53b35ada3ebdaffa71cd1a69db56f399d7571fb59eb9d6d3e73efb3ef2b5cf5eab6dadebd57ebef27d9edef7bc28ae27afab55eb3d14cf7ffaf7e9e8e82ae69afcfc0258b56a0dcd3d6499a317e517be51df8be4ee40e9fd3bf577c5c73788e090080685c71211395821910a89571ca3a28688e820917071ae9028798c53488490eae73d959ac7a3e26b5d6b70cde3118aeb3c398fa8f55854f57978addb118aeb0cae7e2cead96b4548119f2d2363246e6eee8a5287d23770b07712c767c5d1b13fae6e9e840d8ac4c5c54ddc76c6c9c959717f7d229fe354fbbce6354e2fb886d333af7b7adba946aa3f8b531deff7f439a1a16178fbf8557f7ef1f7d82ba4bf3877144747faf573c0d6d64ef13797969636b8f87f738c375ef6843b77eeb078f162929393898d8d23212141d124518a3c1f3e7cb8a2154641e12466cc99ab982c4b96923c429ca7d4886c9fa194d4d45421698aa38c28a4c89a4b4a917b7cea12f9b87c7ef5355215d753bca7f87cd59fa5faf32865685212438726919838949c9c1cb2b3b3993871220505058c1b374e51bc558abc2d459131bce06d4582a6744a656322a5c882fab56fbf48e4735e24ebd7afffdd7db55f27df4b56759122d7de6acbaa55ab1452fb5cca277b76b36ddb7656ae5cada8a5203fb38cd66437d4152b56b074e952b66eddaaf0851a5290f60f05ab76fefebffef52fc55a9174ec2570f7eedde39ffffc27fff8c73ff88738fef6db7ff10fe11c4a0751def76bad73a53c7ffb4522af5997bcecb5b5df53f91ae57d4a91f330ca6bd5feacf2f89bb8ff5ffffdaf173aa4ff19af01acff8cff8cff80f59ff1a78fff0733b17e3822111efc0000000049454e44ae426082, 1);

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
('nhanvien', '9B84756F9A50CC0D8223B9A03842CAC4', 2, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `baohanh`
--
ALTER TABLE `baohanh`
  ADD PRIMARY KEY (`ma_BH`),
  ADD UNIQUE KEY `nhanvien_Tra` (`nhanvien_Tra`),
  ADD UNIQUE KEY `UK_3wsc3w3hlrxns7qbjed5381fo` (`nhanvien_Tra`),
  ADD KEY `sohd_Ban` (`sohd_Ban`),
  ADD KEY `ma_SP` (`ma_SP`),
  ADD KEY `nhanvien_Nhan` (`nhanvien_Nhan`);

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
  ADD KEY `fk_HDM_NCC` (`ma_NCC`),
  ADD KEY `ma_NV` (`ma_NV`);

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
  ADD PRIMARY KEY (`ma_KH`),
  ADD UNIQUE KEY `soCMND_KH` (`soCMND_KH`);

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
  ADD PRIMARY KEY (`ma_NV`),
  ADD UNIQUE KEY `soCMND_NV` (`soCMND_NV`);

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
  MODIFY `ma_BH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `ctkm`
--
ALTER TABLE `ctkm`
  MODIFY `ma_KM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `hoadonban`
--
ALTER TABLE `hoadonban`
  MODIFY `sohd_Ban` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `hoadonmua`
--
ALTER TABLE `hoadonmua`
  MODIFY `sohd_Mua` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `ma_KH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  MODIFY `ma_nsx` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `phanquyen`
--
ALTER TABLE `phanquyen`
  MODIFY `ma_PhanQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `ma_SP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `baohanh`
--
ALTER TABLE `baohanh`
  ADD CONSTRAINT `baohanh_ibfk_1` FOREIGN KEY (`sohd_Ban`) REFERENCES `hoadonban` (`sohd_Ban`),
  ADD CONSTRAINT `baohanh_ibfk_2` FOREIGN KEY (`ma_SP`) REFERENCES `sanpham` (`ma_SP`),
  ADD CONSTRAINT `baohanh_ibfk_3` FOREIGN KEY (`nhanvien_Nhan`) REFERENCES `nhanvien` (`ma_NV`),
  ADD CONSTRAINT `baohanh_ibfk_4` FOREIGN KEY (`nhanvien_Tra`) REFERENCES `nhanvien` (`ma_NV`);

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
  ADD CONSTRAINT `hoadonmua_ibfk_1` FOREIGN KEY (`ma_NCC`) REFERENCES `nhacungcap` (`ma_NCC`),
  ADD CONSTRAINT `hoadonmua_ibfk_2` FOREIGN KEY (`ma_NV`) REFERENCES `nhanvien` (`ma_NV`);

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
