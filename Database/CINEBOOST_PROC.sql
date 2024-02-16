-- QUẢN LÝ ĐỒ ĂN
if OBJECT_ID('sp_qldoan') is not null
drop proc sp_qldoan
go

create proc sp_qldoan 
as
begin
	select DOAN.ID_DOAN, DOAN.TEN, DOAN.ID_LOAI, KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
 end
 go

----- TONG HOP THONG KE--------------------------------------
-- THỐNG KÊ DOANH THU ĐỒ ĂN
if OBJECT_ID('sp_doanhthudoan') is not null
	drop proc sp_doanhthudoan
go

create proc sp_doanhthudoan
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end

--exec sp_doanhthudoan

-- SẮP XẾP SỐ LƯỢNG ĐỒ ĂN
if OBJECT_ID('sp_sapxepdoan') is not null
	drop proc sp_sapxepdoan
go

create proc sp_sapxepdoan
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA				
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
order by SOLUONG desc
end

--exec sp_sapxepdoan

-- TRUY VẤN DOANH THU ĐỒ ĂN
	-- THEO NĂM
if OBJECT_ID('sp_doanhthudoan_theonam') is not null
	drop proc sp_doanhthudoan_theonam
go

create proc sp_doanhthudoan_theonam (@Year int)
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
	WHERE YEAR(NGAYDAT) = @Year
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end

--exec sp_doanhthudoan_theonam '2021'

	-- THEO THÁNG - NĂM
if OBJECT_ID('sp_doanhthudoan_theothangnam') is not null
	drop proc sp_doanhthudoan_theothangnam
go

create proc sp_doanhthudoan_theothangnam (@Thang int, @Year int)
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
	WHERE MONTH(NGAYDAT) = @Thang and YEAR(NGAYDAT) = @Year 
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end

--exec sp_doanhthudoan_theothangnam '2','2021'
--exec sp_doanhthudoan_theothangnam '3','2021'
--exec sp_doanhthudoan_theothangnam '5','2021'

	-- THEO NGÀY
if OBJECT_ID('sp_doanhthudoan_theongay') is not null
	drop proc sp_doanhthudoan_theongay
go

create proc sp_doanhthudoan_theongay (@Ngay date)
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
		where NGAYDAT BETWEEN (convert(varchar, @Ngay) +' 00:00:00') AND (convert(varchar, @Ngay) + ' 23:59:59')
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end

--exec sp_doanhthudoan_theongay '2021-02-16'
--exec sp_doanhthudoan_theongay '2021-03-17'
--exec sp_doanhthudoan_theongay '2021-05-18'


	-- THEO TÊN
if OBJECT_ID('sp_doanhthudoan_theoten') is not null
	drop proc sp_doanhthudoan_theoten
go

create proc sp_doanhthudoan_theoten (@ten nvarchar(100))
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
	WHERE DOAN.TEN like @ten
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end

--exec sp_doanhthudoan_theoten N'Trà sữa'

	-- THEO SIZE
if OBJECT_ID('sp_doanhthudoan_theosize') is not null
	drop proc sp_doanhthudoan_theosize
go

create proc sp_doanhthudoan_theosize (@size nchar(1))
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
	WHERE KICHCODOAN.ID_KICHCO like @size
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end

--exec sp_doanhthudoan_theosize 'S'
--exec sp_doanhthudoan_theosize 'M'
--exec sp_doanhthudoan_theosize 'L'


-- QUẢN LÝ SUẤT CHIẾU
if OBJECT_ID('sp_qlsuatchieu') is not null
	drop proc sp_qlsuatchieu
go

create proc sp_qlsuatchieu
as 
	begin
SELECT PHONGCHIEU.ID_PHONG, PHIM.TEN, CONCAT(convert(varchar, GIOBATDAU, 0), '     ', NGAYCHIEU) as NgayGio
FROM PHONGCHIEU JOIN SUATCHIEU ON PHONGCHIEU.ID_PHONG = SUATCHIEU.ID_PHONG
				JOIN PHIM ON PHIM.ID_PHIM = SUATCHIEU.ID_PHIM
END
GO

--EXEC sp_qlsuatchieu


 -- THỐNG KÊ DOANH THU PHÒNG VÉ
 if OBJECT_ID('sp_doanhthuphongve') is not null
	drop proc sp_doanhthuphongve
go

create proc sp_doanhthuphongve
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
	end

--exec sp_doanhthuphongve

-- SẮP XẾP DOANH THU PHÒNG VÉ
if OBJECT_ID('sp_doanhthuphongve_sapxep') is not null
	drop proc sp_doanhthuphongve_sapxep
go

create proc sp_doanhthuphongve_sapxep
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
		order by sovedaban desc
	end

--exec sp_doanhthuphongve_sapxep

-- TRUY VẤN DOANH THU PHÒNG VÉ
	-- THEO NGÀY
 if OBJECT_ID('sp_doanhthuphongve_theongay') is not null
	drop proc sp_doanhthuphongve_theongay
go

create proc sp_doanhthuphongve_theongay (@Ngay date)
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where NGAYCHIEU BETWEEN (convert(varchar, @Ngay) +' 00:00:00') AND (convert(varchar, @Ngay) + ' 23:59:59')
		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
	end

--exec sp_doanhthuphongve_theongay '2022-03-20'
--exec sp_doanhthuphongve_theongay '2022-03-19'
--exec sp_doanhthuphongve_theongay '2022-03-18'

-- THEO THÁNG - NĂM
 if OBJECT_ID('sp_doanhthuphongve_theothangnam') is not null
	drop proc sp_doanhthuphongve_theothangnam
go

create proc sp_doanhthuphongve_theothangnam (@Thang int, @Year int)
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where MONTH(NGAYCHIEU) like @Thang and YEAR(NGAYCHIEU) like @Year
		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
	end

--exec sp_doanhthuphongve_theothangnam '3','2022'

-- THEO NĂM
 if OBJECT_ID('sp_doanhthuphongve_theonam') is not null
	drop proc sp_doanhthuphongve_theonam
go

create proc sp_doanhthuphongve_theonam (@Year int)
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where YEAR(NGAYCHIEU) like @Year
		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
	end

--exec sp_doanhthuphongve_theonam '2022'


-- THỐNG KÊ LƯỢNG VÉ ĐÃ BÁN
if OBJECT_ID('sp_luongvedaban') is not null
	drop proc sp_luongvedaban
go

create proc sp_luongvedaban
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select DATENAME(dw, SC.NGAYCHIEU) as ngaytrongtuan, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu from SUATCHIEU SC 
		left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		group by SC.NGAYCHIEU, tongsuatchieu
	end

--exec sp_luongvedaban

-- SẮP XẾP LƯỢNG VÉ ĐÃ BÁN
if OBJECT_ID('sp_luongvedaban_sapxep') is not null
	drop proc sp_luongvedaban_sapxep
go

create proc sp_luongvedaban_sapxep
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select DATENAME(dw, SC.NGAYCHIEU) as ngaytrongtuan, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu from SUATCHIEU SC 
		left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		group by SC.NGAYCHIEU, tongsuatchieu
		order by sovedaban desc
	end

--exec sp_luongvedaban_sapxep

-- TRUY VẤN LƯỢNG VÉ ĐÃ BÁN
	-- THEO NGÀY TRONG TUẦN ???
if OBJECT_ID('sp_luongvedaban_theongay') is not null
	drop proc sp_luongvedaban_theongay
go

create proc sp_luongvedaban_theongay (@ngay varchar(20))
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select DATENAME(dw, SC.NGAYCHIEU) as ngaytrongtuan, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu from SUATCHIEU SC 
		left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
		join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		where @ngay like DATENAME(dw, SC.NGAYCHIEU)
		group by SC.NGAYCHIEU, tongsuatchieu
	end

--exec sp_luongvedaban_theongay 'Saturday'

	-- THEO THÁNG - NĂM
if OBJECT_ID('sp_luongvedaban_theothangnam') is not null
	drop proc sp_luongvedaban_theothangnam
go

create proc sp_luongvedaban_theothangnam (@Thang int, @Year int)
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select DATENAME(dw, SC.NGAYCHIEU) as ngaytrongtuan, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu 
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
		join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		where MONTH(SC.NGAYCHIEU) like @Thang and YEAR(sc.NGAYCHIEU) like @Year
		group by SC.NGAYCHIEU, tongsuatchieu
	end

--exec sp_luongvedaban_theothangnam '3','2022'

	-- THEO NĂM
if OBJECT_ID('sp_luongvedaban_theonam') is not null
	drop proc sp_luongvedaban_theonam
go

create proc sp_luongvedaban_theonam (@Year int)
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select DATENAME(dw, SC.NGAYCHIEU) as ngaytrongtuan, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu 
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
		join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		where YEAR(sc.NGAYCHIEU) like @Year
		group by SC.NGAYCHIEU, tongsuatchieu
	end

--exec sp_luongvedaban_theonam '2022'


-- THỐNG KÊ DOANH THU PHIM
if OBJECT_ID('sp_doanhthuphim') is not null
	drop proc sp_doanhthuphim
go

create proc sp_doanhthuphim
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

					group by P.TEN, sosuatchieu
	end
go
--exec sp_doanhthuphim

-- SẮP XẾP DOANH THU PHIM
if OBJECT_ID('sp_doanhthuphim_sapxep') is not null
	drop proc sp_doanhthuphim_sapxep
go

create proc sp_doanhthuphim_sapxep
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

					group by P.TEN, sosuatchieu
					order by sovedat desc
	end
go
--exec sp_doanhthuphim_xapxep

-- TOP 5 DOANH THU PHIM
if OBJECT_ID('sp_doanhthuphim_top') is not null
	drop proc sp_doanhthuphim_top
go

create proc sp_doanhthuphim_top
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select top (5) P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

					group by P.TEN, sosuatchieu
					order by tonggia desc
	end
go
--exec sp_doanhthuphim_top

-- TRUY VẤN DOANH THU PHIM
	-- THEO NGÀY
if OBJECT_ID('sp_doanhthuphim_theongay') is not null
	drop proc sp_doanhthuphim_theongay
go

create proc sp_doanhthuphim_theongay (@Ngay date)
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where sc.NGAYCHIEU BETWEEN (convert(varchar, @Ngay) +' 00:00:00') AND (convert(varchar, @Ngay) + ' 23:59:59')
		group by P.TEN, sosuatchieu
	end
go

--exec sp_doanhthuphim_theongay '2022-03-20'
--exec sp_doanhthuphim_theongay '2022-03-19'
--exec sp_doanhthuphim_theongay '2022-03-18'

	-- THEO THÁNG - NĂM
if OBJECT_ID('sp_doanhthuphim_theothangnam') is not null
	drop proc sp_doanhthuphim_theothangnam
go

create proc sp_doanhthuphim_theothangnam (@Thang int, @Year int)
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where MONTH(sc.NGAYCHIEU) like @Thang and YEAR(sc.NGAYCHIEU) like @Year
		group by P.TEN, sosuatchieu
	end
go

--exec sp_doanhthuphim_theothangnam '3','2022'

	-- THEO NĂM
if OBJECT_ID('sp_doanhthuphim_theonam') is not null
	drop proc sp_doanhthuphim_theonam
go

create proc sp_doanhthuphim_theonam (@Year int)
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where YEAR(sc.NGAYCHIEU) like @Year
		group by P.TEN, sosuatchieu
	end
go

--exec sp_doanhthuphim_theonam '2022'

-- DOANH THU TONG ------------------------
if OBJECT_ID('sp_doanhthutong') is not null
	drop proc sp_doanhthutong
go

create proc sp_doanhthutong
as 
	begin
		-- DOANH THU DO AN
		declare @doanhthuDA table (id int identity(1,1), doanhthuDA decimal(6, 2))
		insert into @doanhthuDA
		select SUM(SOLUONG*GIA) as DT_DOAN 
		from DOANCT DACT join KICHCODOAN KCDA on DACT.ID_KCDA = KCDA.ID_KCDA
						 join DONTHANHTOAN DTT on DTT.ID_DONDA = DACT.ID_DONDA

		-- DOANH THU PHONG VE
		declare @doanhthuPV table (id int identity(1,1), doanhthuPV decimal(6, 2))
		insert into @doanhthuPV
		select SUM(GIA + PHUTHU) as DT_PHIM
		from VEDAT VD join LOAIVE LV on VD.ID_LOAIVE = LV.ID_LOAIVE
					  join GHE G on G.ID_GHE = VD.ID_GHE
					  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
					  join PHONGCHIEU PC on PC.ID_PHONG = G.ID_PHONGCHIEU
					  join SUATCHIEU SC on SC.ID_SUAT = VD.ID_SUAT
		select iif(doanhthuPV is NULL, 0, doanhthuPV) as DTPV, 
			   iif(doanhthuDA is NULL, 0, doanhthuDA) as DTDA, 
			  (iif(doanhthuDA is NULL, 0, doanhthuDA) + iif(doanhthuPV is NULL, 0, doanhthuPV)) as tongDoanhThu
		from @doanhthuDA DTDA join @doanhthuPV DTPV on DTDA.id = DTPV.id
	end

--exec sp_doanhthutong

--- LOC DOANH THU TONG THEO NGAY
if OBJECT_ID('sp_doanhthutong_ngay') is not null
	drop proc sp_doanhthutong_ngay
go

create proc sp_doanhthutong_ngay (@Ngay date)
as 
	begin
		declare @doanhthuDA table (id int identity(1,1), doanhthuDA decimal(6, 2))
		insert into @doanhthuDA
		select SUM(SOLUONG*GIA) as DT_DOAN 
		from DOANCT DACT join KICHCODOAN KCDA on DACT.ID_KCDA = KCDA.ID_KCDA
						 join DONTHANHTOAN DTT on DTT.ID_DONDA = DACT.ID_DONDA
		where NGAYDAT like @Ngay

		-- DOANH THU PV
		declare @doanhthuPV table (id int identity(1,1), doanhthuPV decimal(6, 2))
		insert into @doanhthuPV
		select SUM(GIA + PHUTHU) as DT_PHIM
		from VEDAT VD join LOAIVE LV on VD.ID_LOAIVE = LV.ID_LOAIVE
					  join GHE G on G.ID_GHE = VD.ID_GHE
					  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
					  join PHONGCHIEU PC on PC.ID_PHONG = G.ID_PHONGCHIEU
					  join SUATCHIEU SC on SC.ID_SUAT = VD.ID_SUAT
		where NGAYCHIEU like @Ngay
		select iif(doanhthuPV is NULL, 0, doanhthuPV) as DTPV, 
			   iif(doanhthuDA is NULL, 0, doanhthuDA) as DTDA, 
			   (iif(doanhthuDA is NULL, 0, doanhthuDA) + iif(doanhthuPV is NULL, 0, doanhthuPV)) as tongDoanhThu
		from @doanhthuDA DTDA join @doanhthuPV DTPV on DTDA.id = DTPV.id
end

--exec sp_doanhthutong_nam '2022-03-18'
--exec sp_doanhthutong_nam '2022-03-19'

--- LOC DOANH THU TONG THEO THANG
if OBJECT_ID('sp_doanhthutong_thang') is not null
	drop proc sp_doanhthutong_thang
go

create proc sp_doanhthutong_thang (@Thang int, @Nam int)
as 
	begin
				declare @doanhthuDA table (id int identity(1,1), doanhthuDA decimal(6, 2))
		insert into @doanhthuDA
		select SUM(SOLUONG*GIA) as DT_DOAN 
		from DOANCT DACT join KICHCODOAN KCDA on DACT.ID_KCDA = KCDA.ID_KCDA
						 join DONTHANHTOAN DTT on DTT.ID_DONDA = DACT.ID_DONDA
		where MONTH(NGAYDAT) like @Thang and YEAR(NGAYDAT) like @Nam

		-- DOANH THU PV
		declare @doanhthuPV table (id int identity(1,1), doanhthuPV decimal(6, 2))
		insert into @doanhthuPV
		select SUM(GIA + PHUTHU) as DT_PHIM
		from VEDAT VD join LOAIVE LV on VD.ID_LOAIVE = LV.ID_LOAIVE
					  join GHE G on G.ID_GHE = VD.ID_GHE
					  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
					  join PHONGCHIEU PC on PC.ID_PHONG = G.ID_PHONGCHIEU
					  join SUATCHIEU SC on SC.ID_SUAT = VD.ID_SUAT
		where MONTH(NGAYCHIEU) like @Thang and YEAR(NGAYCHIEU) like @Nam
		select iif(doanhthuPV is NULL, 0, doanhthuPV) as DTPV, 
			   iif(doanhthuDA is NULL, 0, doanhthuDA) as DTDA, 
			   (iif(doanhthuDA is NULL, 0, doanhthuDA) + iif(doanhthuPV is NULL, 0, doanhthuPV)) as tongDoanhThu
		from @doanhthuDA DTDA join @doanhthuPV DTPV on DTDA.id = DTPV.id
	end

--exec sp_doanhthutong_thang '3', '2022'

--- LOC DOANH THU TONG THEO NAM
if OBJECT_ID('sp_doanhthutong_nam') is not null
	drop proc sp_doanhthutong_nam
go

create proc sp_doanhthutong_nam (@Nam int)
as 
	begin
		declare @doanhthuDA table (id int identity(1,1), doanhthuDA decimal(6, 2))
		insert into @doanhthuDA
		select SUM(SOLUONG*GIA) as DT_DOAN 
		from DOANCT DACT join KICHCODOAN KCDA on DACT.ID_KCDA = KCDA.ID_KCDA
						 join DONTHANHTOAN DTT on DTT.ID_DONDA = DACT.ID_DONDA
		where YEAR(NGAYDAT) like @Nam

		-- DOANH THU PV
		declare @doanhthuPV table (id int identity(1,1), doanhthuPV decimal(6, 2))
		insert into @doanhthuPV
		select SUM(GIA + PHUTHU) as DT_PHIM
		from VEDAT VD join LOAIVE LV on VD.ID_LOAIVE = LV.ID_LOAIVE
					  join GHE G on G.ID_GHE = VD.ID_GHE
					  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
					  join PHONGCHIEU PC on PC.ID_PHONG = G.ID_PHONGCHIEU
					  join SUATCHIEU SC on SC.ID_SUAT = VD.ID_SUAT
		where YEAR(NGAYCHIEU) like @Nam
		select iif(doanhthuPV is NULL, 0, doanhthuPV) as DTPV, 
			   iif(doanhthuDA is NULL, 0, doanhthuDA) as DTDA, 
			   (iif(doanhthuDA is NULL, 0, doanhthuDA) + iif(doanhthuPV is NULL, 0, doanhthuPV)) as tongDoanhThu
		from @doanhthuDA DTDA join @doanhthuPV DTPV on DTDA.id = DTPV.id
end

exec sp_doanhthutong_nam '2022'
-- DOANH THU TONG ------------------------


-- TRA CỨU LỊCH CHIẾU
if OBJECT_ID('sp_tracuulichchieu') is not null
drop proc sp_tracuulichchieu
go

create proc sp_tracuulichchieu 
as
begin
	declare @stt table (stt int identity(1,1), idsuat nvarchar(50) )
	insert into @stt
	select ID_SUAT from SUATCHIEU

	select stt, sc.ID_SUAT,p.TEN,sc.ID_PHONG,sc.GIOBATDAU,p.TRANGTHAI,sc.NGAYCHIEU
	from SUATCHIEU sc join PHIM p on sc.ID_PHIM=p.ID_PHIM join @stt s on s.idsuat = sc.ID_SUAT
 end
----- TONG HOP THONG KE--------------------------------------

----- QUAN LY DON THANH TOAN---------------------------------
-- QUẢN LÝ ĐƠN THANH TOÁN
if OBJECT_ID('sp_qldonthanhtoan') is not null
drop proc sp_qldonthanhtoan
go

create proc sp_qldonthanhtoan 
as
begin
	select ID_DONTT, GIOBATDAU as SUATCHIEU, convert(varchar, NGAYDAT, 105) as NGAYDAT, THANHVIEN.ID_TV, TONG
	from DONTHANHTOAN JOIN DONVE ON DONTHANHTOAN.ID_DONVE = DONVE.ID_DONVE
					JOIN THANHVIEN ON THANHVIEN.ID_TV = DONTHANHTOAN.ID_TV
					JOIN VEDAT ON VEDAT.ID_DONVE = DONVE.ID_DONVE
					JOIN SUATCHIEU ON SUATCHIEU.ID_SUAT = VEDAT.ID_SUAT
	 GROUP BY  ID_DONTT, SUATCHIEU.GIOBATDAU,SUATCHIEU.NGAYCHIEU, NGAYDAT, TONG, THANHVIEN.ID_TV
end
go

-- TRUY VAN DON THANH TOAN THEO NGAY MUA 
if OBJECT_ID('sp_donThanhToan_NgayMua') is not null
drop proc sp_donThanhToan_NgayMua
go

create proc sp_donThanhToan_NgayMua (@ngayMua date)
as
begin
	select ID_DONTT, GIOBATDAU as SUATCHIEU, convert(varchar, NGAYDAT, 105) as NGAYDAT, THANHVIEN.ID_TV, TONG
	from DONTHANHTOAN JOIN DONVE ON DONTHANHTOAN.ID_DONVE = DONVE.ID_DONVE
					JOIN THANHVIEN ON THANHVIEN.ID_TV = DONTHANHTOAN.ID_TV
					JOIN VEDAT ON VEDAT.ID_DONVE = DONVE.ID_DONVE
					JOIN SUATCHIEU ON SUATCHIEU.ID_SUAT = VEDAT.ID_SUAT
	where NGAYDAT BETWEEN (convert(varchar, @ngayMua) +' 00:00:00') AND (convert(varchar, @ngayMua) + ' 23:59:59')
	GROUP BY  ID_DONTT, SUATCHIEU.GIOBATDAU,SUATCHIEU.NGAYCHIEU, NGAYDAT, TONG, THANHVIEN.ID_TV
end
go

-- TRUY VAN DON THANH TOAN THEO KHACH HANG
if OBJECT_ID('sp_donThanhToan_KhachHang') is not null
drop proc sp_donThanhToan_KhachHang
go

create proc sp_donThanhToan_KhachHang(@loaiKH nvarchar(10))
as
begin
	declare @loaiKH_search varchar
	if @loaiKH like N'Thành viên'
		set @loaiKH_search = '%TV%'
	else
		set @loaiKH_search = 'NULL'
	select ID_DONTT, GIOBATDAU as SUATCHIEU, convert(varchar, NGAYDAT, 105) as NGAYDAT, THANHVIEN.ID_TV, TONG
	from DONTHANHTOAN JOIN DONVE ON DONTHANHTOAN.ID_DONVE = DONVE.ID_DONVE
					JOIN THANHVIEN ON THANHVIEN.ID_TV = DONTHANHTOAN.ID_TV
					JOIN VEDAT ON VEDAT.ID_DONVE = DONVE.ID_DONVE
					JOIN SUATCHIEU ON SUATCHIEU.ID_SUAT = VEDAT.ID_SUAT
	where THANHVIEN.ID_TV like @loaiKH_search
	GROUP BY  ID_DONTT, SUATCHIEU.GIOBATDAU,SUATCHIEU.NGAYCHIEU, NGAYDAT, TONG, THANHVIEN.ID_TV
end
go

-- XOA DON THANH TOAN 
if(OBJECT_ID('tg_xoa_donThanhToan')) is not null
	drop trigger tg_xoa_donThanhToan
go

create trigger tg_xoa_donThanhToan on DONTHANHTOAN instead of delete
as
	begin
		delete from DONTHANHTOAN where ID_DONTT like (select ID_DONTT from deleted)
		delete from VEDAT where ID_DONVE like (select ID_DONVE from deleted)
		delete from DONVE where ID_DONVE like (select ID_DONVE from deleted)
		delete from DOANCT where ID_DONDA like (select ID_DONDA from deleted)
		delete from DONDOAN where ID_DONDA like (select ID_DONDA from deleted)
	end
----- QUAN LY DON THANH TOAN---------------------------------

----- HOA DON CHI TIET---------------------------------------
-- DON VE 
if OBJECT_ID('sp_donVe') is not null
drop proc sp_donVe
go

create proc sp_donVe (@id_dontt int)
as
begin
	select P.TEN, 
		   convert(varchar, GIOBATDAU, 0) + ' ' + convert(varchar, NGAYCHIEU, 105) as SUATCHIEU,
		   VDAT.ID_LOAIVE,
		   convert(float,(GIA + PHUTHU)) as GIA
	from DONTHANHTOAN DTT join DONVE DV on DTT.ID_DONVE = DV.ID_DONVE
						  join VEDAT VDAT on VDAT.ID_DONVE = DV.ID_DONVE
						  join SUATCHIEU SC on SC.ID_SUAT = VDAT.ID_SUAT
						  join PHIM P on P.ID_PHIM = SC.ID_PHIM
						  join LOAIVE LV on LV.ID_LOAIVE = VDAT.ID_LOAIVE
						  join GHE G on G.ID_GHE = VDAT.ID_GHE
						  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
	where ID_DONTT = @id_dontt
	group by ID_VEDAT, P.TEN, GIOBATDAU, NGAYCHIEU, VDAT.ID_LOAIVE, GIA, PHUTHU
end
go

-- DON DO AN 
if OBJECT_ID('sp_donDoAn') is not null
drop proc sp_donDoAn
go

create proc sp_donDoAn (@id_dontt int)
as
begin
	select TEN, SOLUONG, ID_KICHCO, convert(float,sum(GIA)) as TONGTIEN
	from DONTHANHTOAN DTT join DONDOAN DDA on DTT.ID_DONDA = DDA.ID_DONDA
						  join DOANCT DACT on DACT.ID_DONDA = DDA.ID_DONDA
						  join KICHCODOAN KCDA on KCDA.ID_KCDA = DACT.ID_KCDA
						  join DOAN DA on DA.ID_DOAN = KCDA.ID_DOAN
	where ID_DONTT = @id_dontt
	group by TEN, SOLUONG, ID_KICHCO
end
go
----- HOA DON CHI TIET---------------------------------------

----- QUAN LY DO AN KEM -------------------------------------
-- THEM DO AN
if OBJECT_ID('sp_themDoAn') is not null
	drop proc sp_themDoAn
go

create proc sp_themDoAn (@tenDA nvarchar(50), @id_loaiDA varchar(2), @id_kichCo varchar(1), @gia float)
as
	begin
		insert into DOAN(TEN, ID_LOAI) 
		values (@tenDA, @id_loaiDA)

		declare @id_da varchar(10)
		select @id_da =  ID_DOAN from DOAN where TEN like @tenDA and ID_LOAI like @id_loaiDA

		insert into KICHCODOAN(ID_DOAN, ID_KICHCO, GIA) 
		values (@id_da, @id_kichCo, @gia)
	end
go

--exec sp_themDoAn N'CHANH VẮT', 'DU', 'S', 30
---------------------------------------------
-- SUA DO AN
if OBJECT_ID('sp_suaDoAn') is not null
	drop proc sp_suaDoAn
go

create proc sp_suaDoAn (@tenDA nvarchar(50), @id_loaiDA varchar(2), @id_kichCo varchar(1), @gia float, @id_DoAn varchar(10), @id_KCDA int)
as
	begin
		update DOAN
		
		set TEN = @tenDA, ID_LOAI = @id_loaiDA
		where ID_DOAN like @id_DoAn
		update KICHCODOAN
		set ID_KICHCO = @id_kichCo, GIA = @gia
		where ID_DOAN like @id_KCDA
	end
go

--exec sp_suaDoAn N'CAM', 'TA', 'M', 40, 'DU00002', 4
---------------------------------------------
-- XOA DO AN
if OBJECT_ID('tg_xoaDoAn') is not null
	drop trigger tg_xoaDoAn
go

create trigger tg_xoaDoAn on DOAN instead of delete
as
	begin
		delete from KICHCODOAN where ID_DOAN like (select ID_DOAN from deleted)
		delete from DOAN where ID_DOAN like (select ID_DOAN from deleted)		
	end
go

--delete DOAN where ID_DOAN like 'DU00006'
---------------------------------------------
----- QUAN LY DO AN KEM -------------------------------------

  -- 1 lon hon hoac = @ngay | 0 bang @ngay | -1 nho hon ngay 
  IF(OBJECT_ID('SP_LICHCHIEUNGAY')) IS NOT NULL
	DROP PROC SP_LICHCHIEUNGAY
  GO
  CREATE PROC SP_LICHCHIEUNGAY @NGAY DATE, @GIO TIME = NULL, @CONDITION TINYINT = 1
  AS BEGIN

  DECLARE @Lichchieu TABLE(
  ID_SUAT NVARCHAR(30),
  NGAYCHIEU DATE,
  ID_PHONG NCHAR(4),
  ID_PHIM NVARCHAR(15),
  GIOBATDAU TIME,
  GIOKETTHUC TIME,
  ID_NV NVARCHAR(10)
  )
	IF(@CONDITION = 1) BEGIN
		INSERT INTO @Lichchieu
					SELECT SC.* 
					 FROM PHIM P
					 JOIN SUATCHIEU SC ON SC.ID_PHIM = P.ID_PHIM
					 WHERE TRANGTHAI = 1 AND NGAYCHIEU >= @NGAY 
					 GROUP BY SC.ID_PHIM, TEN,  SC.ID_SUAT, SC.NGAYCHIEU, SC.ID_PHONG, SC.GIOBATDAU, SC.GIOKETTHUC, SC.ID_NV
					 ORDER BY NGAYCHIEU OFFSET 0 ROWS
					 END
	IF(@CONDITION = 0) BEGIN
	INSERT INTO @Lichchieu
				SELECT SC.* FROM PHIM P
				 JOIN SUATCHIEU SC ON SC.ID_PHIM = P.ID_PHIM
				 WHERE TRANGTHAI = 1 AND NGAYCHIEU = @NGAY
				 GROUP BY SC.ID_PHIM, TEN,  SC.ID_SUAT, SC.NGAYCHIEU, SC.ID_PHONG, SC.GIOBATDAU, SC.GIOKETTHUC, SC.ID_NV
				 ORDER BY NGAYCHIEU OFFSET 0 ROWS
				 END
	IF(@CONDITION = -1) BEGIN
	INSERT INTO @Lichchieu
				SELECT SC.* FROM PHIM P
				 JOIN SUATCHIEU SC ON SC.ID_PHIM = P.ID_PHIM
				 WHERE TRANGTHAI = 1 AND NGAYCHIEU < @NGAY
				 GROUP BY SC.ID_PHIM, TEN,  SC.ID_SUAT, SC.NGAYCHIEU, SC.ID_PHONG, SC.GIOBATDAU, SC.GIOKETTHUC, SC.ID_NV
				 ORDER BY NGAYCHIEU OFFSET 0 ROWS			 
				END

	IF(@GIO IS NOT NULL)
	BEGIN 
		SELECT * FROM @Lichchieu WHERE GIOBATDAU > @GIO
	END
	ELSE SELECT * FROM @Lichchieu
  END
  GO

  EXEC SP_LICHCHIEUNGAY '2022-03-19', '7:00:00',1


  GO

  IF (OBJECT_ID('SP_FINDTIMESLOT')) IS NOT NULL
DROP PROC SP_FINDTIMESLOT
GO

CREATE PROC SP_FINDTIMESLOT  @NGAY DATE, @PHONG NCHAR(4), @GIOBD TIME, @GIOKT TIME
AS BEGIN
--	declare @giobd time = cast ('08:40:00' as time);
--declare @gioKT time = cast ('08:45:00' as time);
--declare @ngay date = '2022-03-20';
--declare @phong nvarchar(4) = 'P001'

select * from SUATCHIEU where ID_PHONG = @PHONG and ngaychieu = @ngay 
and (( GIOBATDAU between convert(time, @giobd) and convert(time, @gioKT)) or ( GIOBATDAU <= @gioKT ) AND  (GIOKETTHUC >= @giobd ))
END
GO


	declare @giobd time = cast ('08:50:00' as time);
declare @gioKT time = cast ('10:31:00' as time);
declare @ngay date = '2022-03-20';
declare @phong nvarchar(4) = 'P001'

EXEC SP_FINDTIMESLOT @NGAY, @PHONG, @giobd, @gioKT
--------------------------------------------------------------------
 
 select * from SUATCHIEU where ID_PHONG = @phong and ngaychieu = @ngay


  select VEDAT.*,  (gia + phuthu) tong from VEDAT join ghe g on VEDAT.ID_GHE = g.ID_GHE join LOAIVE lv on VEDAT.ID_LOAIVE = lv.ID_LOAIVE join loaiGhe lg on lg.ID_LOAIGHE = g.ID_LOAIGHE
  where ID_DONVE = 'dv00011'

  select * from DONTHANHTOAN


  SELECT distinct p.* FROM PHIM P JOIN SUATCHIEU SC ON SC.ID_PHIM = P.ID_PHIM  
                                        WHERE SC.ID_SUAT IN ('sc00001','sc00004','SC00090','SC00095', 'SC00096', 'SC00098', 'SC00091', 'SC00092', 'SC00093')


										select * from SUATCHIEU where ID_SUAT = 'SC00086';

										
										select * from VEDAT where ID_SUAT = 'SC00046'


--------------------------------------------
if(OBJECT_ID('tg_xoavedat')) is not null
	drop trigger tg_xoavedat
go
-- create trigger before insert phim
create trigger tg_xoavedat on VEDAT
AFTER DELETE 
AS BEGIN
	DECLARE @ID_DONVE NVARCHAR(20) = (SELECT TOP 1 ID_DONVE FROM deleted)
	DECLARE @VEDAT_NUM INT = (SELECT COUNT(*) FROM VEDAT WHERE ID_DONVE = @ID_DONVE)
	PRINT 'LUONG VE TRONG DON: ' + CAST(@VEdAT_NUM AS NVARCHAR(40)) + ' DONVE: ' + @ID_DONVE

	IF @VEDAT_NUM = 0
	BEGIN
		DELETE FROM DONVE WHERE ID_DONVE = @ID_DONVE
	END 
END
GO


-------------------------------------------------------------------

-------------------------------------------------------------- END -----------------------------------------------------