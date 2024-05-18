CREATE TABLE todos (
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(200),
	detail VARCHAR(500),
	importance INT,
	limit_date DATE
);

INSERT INTO todos (id, title, detail, importance, limit_date)VALUES('1', 'テストテスト', 'SQLの確認テストの採点と報告書を作成する。', '3', '2020-5-20');
INSERT INTO todos (id, title, detail, importance, limit_date)VALUES('2', 'ほげほげ', 'ぴよぴよ', '1', '2019-5-20');
INSERT INTO todos (id, title, detail, importance, limit_date)VALUES('3', 'あああああ', 'いいいいうううう', '2', '2020-5-01');
