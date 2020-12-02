# C1812L-Android
SQLLite:
	- Tao CSDL tren mobile
		- Hoc tao CSDL
		- Hieu co che hoat dong cua CSDL
	- Phan tich du an Food App
		- food
			- _id integer primary key autoincrement
			- title
			- content
			- price

	- CSDL
		- Lay danh sach SP => hien thi ra ListView
		- Them food vao CSDL
		- Sua food vao CSDL
		- Xoa food khoi CSDL
===================================================
<code>
create table food (
	_id integer primary key autoincrement,
	title varchar(50),
	content text,
	price float
)
</code>
