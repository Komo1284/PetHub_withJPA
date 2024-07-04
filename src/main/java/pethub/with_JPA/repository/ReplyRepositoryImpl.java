package pethub.with_JPA.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pethub.with_JPA.dto.*;
import pethub.with_JPA.entity.QReply;

import java.util.List;

import static pethub.with_JPA.entity.QBoard.board;
import static pethub.with_JPA.entity.QReply.*;

@Repository
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReplyRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ReplyListDto> whatIWroteReply(BoardSearchCondition condition, Pageable pageable, Long id) {
        List<ReplyListDto> content = queryFactory
                .select(new QReplyListDto(reply.board.id, reply.board.title, reply.content, reply.w_date))
                .from(reply)
                .where(reply.member.id.eq(id))
                .orderBy(reply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(reply.count())
                .from(reply)
                .where(reply.member.id.eq(id))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
