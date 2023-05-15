DROP TABLE USER_ACCESS;
DROP TABLE USER;
CREATE TABLE USER(
    USER_NO                 NUMBER              NOT NULL,
    ID                      VARCHAR2(40 BYTE),              -- ID 정규식에 반영하기
    PW                      VARCHAR2(64 BYTE)   NOT NULL,   -- SHA-256 암호화 방식 사용
    NAME                    VARCHAR2(40 BYTE),              -- 이름
    GENDER                  VARCHAR2(2 BYTE)    NOT NULL,   -- M, F, ND
    EMAIL                   VARCHAR2(100 BYTE)  NOT NULL,   -- 이메일
    MOBILE                  VARCHAR2(15 BYTE),              -- 하이픈 제외하고 저장 
    BIRTHYEAR               VARCHAR2(4 BYTE),
    POST_ADDRESS            VARCHAR2(5 BYTE),
    ROAD_ADDRESS            VARCHAR2(100 BYTE),
    JIBUN_ADDRESS           VARCHAR2(100 BYTE),
    DETAIL_ADDRESS          VARCHAR2(100 BYTE),
    EXTRA_ADDRESS           VARCHAR2(100 BYTE),
    AGREECODE               NUMBER              NOT NULL,   -- 동의여부(0필수 1위치 2이벤트 3위치+이벤트
    JOINED_AT               DATE,
    PW_MODIFIED_AT          DATE,                           -- 비밀번호 변경일
    AUTOLOGIN_ID            VARCHAR2(32 BYTE),              -- 자동 로그인 할 때 사용하는 ID(SESSION_ID를 이용함)
    AUTOLOGIN_EXPIRED_AT    DATE
);

-- 회원 접속 기록(회원마다 마지막 로그인 날짜 1개만 기록)
CREATE TABLE USER_ACCESS(
    ID              VARCHAR2(40 BYTE) NOT NULL UNIQUE,  -- 로그인 한 사용자 id
    LAST_LOGIN_AT   DATE                                -- 마지막 로그인 날짜
);

ALTER TABLE USER
    ADD CONSTRAINT PK_USER
        PRIMARY KEY(USER_NO);
        
ALTER TABLE USER_ACCESS
    ADD CONSTRAINT FK_USER_ACCESS
        FOREIGN KEY(ID) REFERENCES USER(ID)
            ON DELETE CASCADE;
            
DROP SEQUENCE USER_SEQ;
CREATE SEQUENCE USER_SEQ NOCACHE;

-- 탈퇴 (탈퇴한 아이디로 재가입이 불가능)
DROP TABLE LEAVE_USER;
CREATE TABLE LEAVE_USER(
    ID          VARCHAR2(40 BYTE) NOT NULL UNIQUE,
    JOINED_AT   DATE,
    LEAVED_AT   DATE
);

DROP TABLE SLEEP_USER;
CREATE TABLE SLEEP_USER(
    USER_NO                 NUMBER              NOT NULL,
    ID                      VARCHAR2(40 BYTE),              -- ID 정규식에 반영하기
    PW                      VARCHAR2(64 BYTE)   NOT NULL,   -- SHA-256 암호화 방식 사용
    NAME                    VARCHAR2(40 BYTE),              -- 이름
    GENDER                  VARCHAR2(2 BYTE)    NOT NULL,   -- M, F, ND
    EMAIL                   VARCHAR2(100 BYTE)  NOT NULL,   -- 이메일
    MOBILE                  VARCHAR2(15 BYTE),              -- 하이픈 제외하고 저장 
    BIRTHYEAR               VARCHAR2(4 BYTE),
    POST_ADDRESS            VARCHAR2(5 BYTE),
    ROAD_ADDRESS            VARCHAR2(100 BYTE),
    JIBUN_ADDRESS           VARCHAR2(100 BYTE),
    DETAIL_ADDRESS          VARCHAR2(100 BYTE),
    EXTRA_ADDRESS           VARCHAR2(100 BYTE),
    AGREECODE               NUMBER              NOT NULL,   -- 동의여부(0필수 1위치 2이벤트 3위치+이벤트
    JOINED_AT               DATE,
    PW_MODIFIED_AT          DATE,                           -- 비밀번호 변경일
    SLEPT                   DATE                            --휴먼일

);
