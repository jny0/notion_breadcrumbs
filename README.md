# notion_breadcrumbs

## 목표

노션과 유사한 간단한 페이지 관리 API를 구현해주세요. 각 페이지는 제목, 컨텐츠, 그리고 서브 페이지를 가질 수 있습니다. 또한, 특정 페이지에 대한 브로드 크럼스(Breadcrumbs) 정보도 반환해야 합니다.

<br/>

## 요구사항

**페이지 정보 조회 API**: 특정 페이지의 정보를 조회할 수 있는 API를 구현하세요.

- 입력: 페이지 ID
- 출력: 페이지 제목, 컨텐츠, 서브 페이지 리스트, **브로드 크럼스 ( 페이지 1 > 페이지 3 > 페이지 5)**
- 컨텐츠 내에서 서브페이지 위치 고려  X

<br/>

## 테이블 구조
![image](https://github.com/jny0/notion_breadcrumbs/assets/94813918/62e9ba50-20df-4e3a-958f-7760ce179ad6)


<br/>

## 비즈니스 로직

**1. 페이지 기본 정보 조회**
  - 요청된 페이지 id를 사용해서 DB에서 해당 페이지의 기본 정보(제목, 내용) 조회
<br/>

**2. 서브페이지 조회**
  - 요청된 페이지의 서브페이지(하위페이지) 목록 조회 후 subPages 배열 형태로 반환
  - 서브페이지의 id와 제목 포함

<br/>

**3. 브로드크럼스 조회**
  - 브로드크럼스는 요청된 페이지의 상위 페이지 경로를 나타냄
  - 상위페이지의 id와 제목 포함
  - **재귀쿼리**를 사용하여 상위페이지를 계속 추적하면서 브로드크럼스를 구성
    
      ```sql
      WITH RECURSIVE BreadcrumbHierarchy AS (
          SELECT id, title, parent_id FROM pages WHERE id = ?
          UNION ALL
          SELECT p.id, p.title, p.parent_id FROM pages p
          INNER JOIN BreadcrumbHierarchy b ON p.id = b.parent_id
      )
      SELECT id, title FROM BreadcrumbHierarchy;
      ORDER BY parent_id
      ```
- 재귀쿼리를 사용한 이유
  - 페이지 깊이에 제한이 없기 때문에 재귀쿼리를 사용하지 않으면 부모 페이지에 대한 조회쿼리를 일일히 날려주어야 하고, DB 부하가 늘어나 성능 이슈가 발생할 가능성이 높다.


<br/>


## 결과 정보
5번 페이지 조회 예시

브로드크럼스 :  `1 > 3 > 5 > 8`

```JSON
{
    "pageId": 5,
    "title": "페이지 5",
    "content": "페이지 5의 내용입니다.",
    "subPages": [
        {
            "pageId": 8,
            "title": "페이지 8"
        }
    ],
    "breadcrumbs": [
        {
            "pageId": 1,
            "title": "페이지 1"
        },
        {
            "pageId": 3,
            "title": "페이지 3"
        },
        {
            "pageId": 5,
            "title": "페이지 5"
        }
    ]
}
```
