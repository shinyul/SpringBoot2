# SpringBoot2 RestAPI Project Setting
Spring Boot2 프로젝트를 구성 및 추가하며 마지막 리팩토링 진행 예정 

- version 1 : firebase Token 을 받아서 인증 및 권한 부여 하고 인증 처리 하여 서비스 RestAPI 구성 
              ( Token 은 FrontEnd 에서 발급 받음 ) 


## Development environment
 - Spring Boot 2
 - Spring Security 
 - Firebase Token Auth 
 - HikariCP
 - MariaDB
 - JPA
 - Jackson
 - lombok


## Table of Contents

- [Github를 이용한 프로젝트 관리](#github를-이용한-프로젝트-관리)
- [Git Flow를 활용한 프로세스](#git-Flow를-활용한-프로세스)
- [References](#references)

## Github를 이용한 프로젝트 관리

- [Issue 관리](#issue-관리)
- [Commit Message 관리](#commit-message-관리)
- [Work Flow](#work-flow)

### Issue 관리

#### Issue 종류

- `question`
- `enhancement`
- `bug`
- `refactoring`
- `duplicate`
- `critical`
- `invalid`
- `discussion`
- `documentation`

### Commit Message 관리

> <action> <issue number>- message

- `Add` : 신규 파일 추가 및 기능 추가
- `Update` : 코드 변경
- `Remove` : 파일 삭제 및 기능 제거
- `Refactoring` : 리팩토링
- `Fix` : 버그 및 이슈 수정

Example 1. 새로운 기능을 추가했을 때

```bash
git commit -m "Add #1 - 모달 컴포넌트 개발 완료"
```

Example 2. 이슈를 해결했을 때

```bash
git commit -m "Fix #1 - 로그인 오류 수정"
```

### Work Flow

1. 깃허브 마일스톤 생성
2. 이슈 등록(마일스톤과 연결, 없을 경우 이슈만 등록)
3. 작업을 위한 브랜치 생성(git flow를 따름)
4. 이슈 해결 후 커밋 및 푸시
5. `Pull Request`를 통해 Review
6. Review 후 master branch`와 머지
7. 머지된 브랜치 삭제

## Git Flow를 활용한 프로세스

### Branch

- `master`
- `develop`
- `feature/[feature name]`

## References

- [우아한형제 - 우린 Git-flow를 사용하고 있어요](http://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)
- [Github 이슈 사용 방법](https://programmingsummaries.tistory.com/386)
