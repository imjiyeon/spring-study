package com.example.demo.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.processing.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QBoard is a Querydsl query type for Board
 */
// 코드 생성기가 자동으로 생성한 파일이라는 표시
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
// Q도메인 클래스는 EntityPathBase를 상속받음
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -164971147L;

    // 싱글톤 인스턴스로 생성됨
    public static final QBoard board = new QBoard("board");

    public final QBaseEntity _super = new QBaseEntity(this);

    // Board 엔티티의 content 필드를 표현하는 StringPath 객체
    // 이를 사용하여 쿼리를 작성할 수 있음
    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath title = createString("title");

    public final StringPath writer = createString("writer");

    // Q도메인 클래스의 생성자들
    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

