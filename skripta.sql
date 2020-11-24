USE [master]
GO
/****** Object:  Database [ZavrsniRad]    Script Date: 6/23/2020 9:02:38 PM ******/
CREATE DATABASE [ZavrsniRad]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ZavrsniRad', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\ZavrsniRad.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ZavrsniRad_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\ZavrsniRad_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [ZavrsniRad] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ZavrsniRad].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ZavrsniRad] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ZavrsniRad] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ZavrsniRad] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ZavrsniRad] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ZavrsniRad] SET ARITHABORT OFF 
GO
ALTER DATABASE [ZavrsniRad] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [ZavrsniRad] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ZavrsniRad] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ZavrsniRad] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ZavrsniRad] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ZavrsniRad] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ZavrsniRad] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ZavrsniRad] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ZavrsniRad] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ZavrsniRad] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ZavrsniRad] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ZavrsniRad] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ZavrsniRad] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ZavrsniRad] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ZavrsniRad] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ZavrsniRad] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ZavrsniRad] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ZavrsniRad] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ZavrsniRad] SET  MULTI_USER 
GO
ALTER DATABASE [ZavrsniRad] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ZavrsniRad] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ZavrsniRad] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ZavrsniRad] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ZavrsniRad] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ZavrsniRad] SET QUERY_STORE = OFF
GO
USE [ZavrsniRad]
GO
/****** Object:  Table [dbo].[History]    Script Date: 6/23/2020 9:02:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[History](
	[IDHistory] [int] IDENTITY(1,1) NOT NULL,
	[Accuracy] [nvarchar](25) NULL,
	[WPM] [nvarchar](25) NULL,
	[HistoryDate] [date] NULL,
	[Language] [nvarchar](25) NULL,
	[KeyboardLayout] [nvarchar](25) NULL,
	[TypeOfTest] [nvarchar](25) NULL,
	[ProgrammingLanguage] [nvarchar](25) NULL,
	[UsersID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[IDHistory] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 6/23/2020 9:02:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[IDUsers] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](30) NULL,
	[LastName] [nvarchar](30) NULL,
	[Email] [nvarchar](50) NULL,
	[Password] [nvarchar](30) NULL,
	[Images] [varbinary](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[IDUsers] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[History] ON 

INSERT [dbo].[History] ([IDHistory], [Accuracy], [WPM], [HistoryDate], [Language], [KeyboardLayout], [TypeOfTest], [ProgrammingLanguage], [UsersID]) VALUES (1, N'160', N'26.0%', CAST(N'2020-05-21' AS Date), N'English (US)', N'QWERTY', N'C++', N'nyes', 1)
INSERT [dbo].[History] ([IDHistory], [Accuracy], [WPM], [HistoryDate], [Language], [KeyboardLayout], [TypeOfTest], [ProgrammingLanguage], [UsersID]) VALUES (2, N'10.0%', N'240', CAST(N'2020-05-21' AS Date), N'English (US)', N'QWERTY', N'C++', N'nyes', 1)
INSERT [dbo].[History] ([IDHistory], [Accuracy], [WPM], [HistoryDate], [Language], [KeyboardLayout], [TypeOfTest], [ProgrammingLanguage], [UsersID]) VALUES (3, N'10.0%', N'160', CAST(N'2020-05-21' AS Date), N'English (US)', N'QWERTY', N'C++', N'nyes', 1)
INSERT [dbo].[History] ([IDHistory], [Accuracy], [WPM], [HistoryDate], [Language], [KeyboardLayout], [TypeOfTest], [ProgrammingLanguage], [UsersID]) VALUES (4, N'16.0%', N'240', CAST(N'2020-05-21' AS Date), N'English (US)', N'QWERTY', N'C++', N'nyes', 1)
INSERT [dbo].[History] ([IDHistory], [Accuracy], [WPM], [HistoryDate], [Language], [KeyboardLayout], [TypeOfTest], [ProgrammingLanguage], [UsersID]) VALUES (5, N'7.0%', N'480', CAST(N'2020-05-21' AS Date), N'English (US)', N'QWERTY', N'C++', N'nyes', 2)
INSERT [dbo].[History] ([IDHistory], [Accuracy], [WPM], [HistoryDate], [Language], [KeyboardLayout], [TypeOfTest], [ProgrammingLanguage], [UsersID]) VALUES (6, N'26.0%', N'240', CAST(N'2020-05-21' AS Date), N'English (US)', N'QWERTY', N'C++', N'nyes', 1)
SET IDENTITY_INSERT [dbo].[History] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([IDUsers], [FirstName], [LastName], [Email], [Password], [Images]) VALUES (1, N'Mate', N'Kovilnijus', N'kowe@net.hr', N'123', NULL)
INSERT [dbo].[Users] ([IDUsers], [FirstName], [LastName], [Email], [Password], [Images]) VALUES (2, N'Ante', N'Nyes', N'ante@email.hr', N'123', NULL)
SET IDENTITY_INSERT [dbo].[Users] OFF
ALTER TABLE [dbo].[History]  WITH CHECK ADD FOREIGN KEY([UsersID])
REFERENCES [dbo].[Users] ([IDUsers])
GO
/****** Object:  StoredProcedure [dbo].[CHECK_Users]    Script Date: 6/23/2020 9:02:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[CHECK_Users]
@email nvarchar(50)
as
select count(*) from Users
where  Email =@email
GO
/****** Object:  StoredProcedure [dbo].[GET_ALL_USERS]    Script Date: 6/23/2020 9:02:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[GET_ALL_USERS]
as
select IDUsers,FirstName,LastName,Email,Password from Users
GO
/****** Object:  StoredProcedure [dbo].[GET_HISTORY_FOR_USERS]    Script Date: 6/23/2020 9:02:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[GET_HISTORY_FOR_USERS]
@id int
as
select*from History
where UsersID=@id
GO
/****** Object:  StoredProcedure [dbo].[GET_USERS]    Script Date: 6/23/2020 9:02:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[GET_USERS]
@id int
as
select*from Users
where IDUsers=@id
GO
/****** Object:  StoredProcedure [dbo].[INSERT_History]    Script Date: 6/23/2020 9:02:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[INSERT_History]
@HistoryDate date,
@UsersID int,
@Accuracy nvarchar (25),
@Wpm nvarchar (25),
@Lang nvarchar (25),
@Layout nvarchar (25),
@TypeOfTest nvarchar (25),
@ProgrammingLanguage nvarchar(25)
as
Insert into History(HistoryDate,UsersID,Accuracy,Wpm,Language,KeyboardLayout,TypeOfTest,ProgrammingLanguage)
Values(@HistoryDate ,
@UsersID ,
@Accuracy ,
@Wpm ,
@Lang ,
@Layout ,
@TypeOfTest,
@ProgrammingLanguage);
GO
/****** Object:  StoredProcedure [dbo].[INSERT_USERS]    Script Date: 6/23/2020 9:02:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[INSERT_USERS]
@FirstName nvarchar(30),
@LastName nvarchar(30),
@Email nvarchar(50),
@Password nvarchar(30)
as
Insert into Users(FirstName,LastName,Email,Password)
Values(@FirstName,@LastName,@Email,@Password)
GO
USE [master]
GO
ALTER DATABASE [ZavrsniRad] SET  READ_WRITE 
GO
