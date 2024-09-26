package project_1st_team03.dashboard;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project_1st_team03.dashboard.domain.comment.dao.CommentRepository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.dao.PostRepository;
import project_1st_team03.dashboard.domain.post.domain.Post;
import project_1st_team03.dashboard.global.security.MemberRole;

import static project_1st_team03.dashboard.domain.member.domain.Member.createMember;

/**
 * - 테스트 코드가 익숙치 않은 상황에서
 *    테스트 코드를 짜면서 구현하기엔 일정이 촉박
 *
 * - 프론트 연동하면서 작업하기 위한 초기화 데이터
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final EntityManager entityManager;

    // 스프링 컨테이너가 초기화를 전부 끝내고, 실행 준비가 됐을 때 발생하는 이벤트
    @Transactional
    @EventListener(value = ApplicationReadyEvent.class)
    public void initData() {
        log.info("실습용 데이터 초기화");

        String password = "$2a$10$hypLJDgNgKTU6SdmeX9b5eILQONFXeK0FjdB3BoofwIGO/lWUL0kW";

        // TODO 양방향 연관관계, 영속성 전이 너무 어렵다... 다시 제대로 복습 필요...

        Member member1 = createMember("member1@naver.com", password, MemberRole.USER);
        Member member2 = createMember("member2@naver.com", password, MemberRole.USER);
        Member member3 = createMember("member3@naver.com", password, MemberRole.USER);
        Member member4 = createMember("member4@naver.com", password, MemberRole.USER);
        Member member5 = createMember("member5@naver.com", password, MemberRole.USER);
        Member member6 = createMember("member6@naver.com", password, MemberRole.USER);
        Member member7 = createMember("member7@naver.com", password, MemberRole.USER);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);

//        entityManager.flush();
//        entityManager.clear();


        Post post1_1 = postRepository.save(new Post(member1, "오잉 아직 아무도 게시글 안 쓰셨네", "내가 1빠"));
        Post post1_2 = postRepository.save(new Post(member1, "오늘 저녁메뉴 추천 좀 해주세요", "뭐 먹을까요"));

        Post post2 = postRepository.save(new Post(member2, "안녕하십니까", "여긴 뭐하는 곳인가요"));
        Post post3 = postRepository.save(new Post(member3, "안녕하세요! 오늘 가입했어요!!", "인사드립니다!!"));
        Post post4 = postRepository.save(new Post(member4, "나만 고양이 없어", "ㅠㅜㅜ"));
        Post post5 = postRepository.save(new Post(member5, "요즘 날이 선선해진 것 같아요", ""));
        Post post6 = postRepository.save(new Post(member6, "진짜 화가나네요", "진짜로 진짜로 화가 많이 나네요"));
        Post post7 = postRepository.save(new Post(member7, "키토식 + 간헐적 단식 시작하겠습니당..", "간드아"));
        Post post8 = postRepository.save(new Post(member1, "혹시 여러분, 추천해주실만한 운동 있나여", "추천 부탁드립니당"));
        Post post9 = postRepository.save(new Post(member3, "참 막막하네요...", "내 인생..."));
        Post post10 = postRepository.save(new Post(member7, "식단 좀 봐주세요", "닭 백숙"));

        Post post11 = postRepository.save(new Post(member2, "노트북 추천 좀요", "뭐 살까요"));
        Post post12 = postRepository.save(new Post(member3, "전 이만 떠나겠습니다..", "여러분 잘 지내요"));
        Post post13 = postRepository.save(new Post(member4, "고양이한테 간택 받았어요!!", "나도 이제 집사다!!"));
        Post post14 = postRepository.save(new Post(member5, "이제 금방 추워지겠죠?", "가을이 너무 짧네요"));
        Post post15 = postRepository.save(new Post(member6, "!!!진짜 화가나네요!!!!!!!", "다 부숴버린드아아"));
        Post post16 = postRepository.save(new Post(member7, "케톤측정기 샀어용, 여러분", "신기해용"));
        Post post17 = postRepository.save(new Post(member1, "운동 중 근육파열...", "하하하..."));
        Post post18 = postRepository.save(new Post(member7, "탄수화물 끊기시작하니까...", "얼굴 트러블이 줄었어요!!"));

        Post post19 = postRepository.save(new Post(member2, "노트북 샀는데요...", "흠.. 생각보단 .. 좋은지 모르겠네요"));
        Post post20 = postRepository.save(new Post(member4, "동물병원비 비싼줄은 알았지만...", "진짜 장난 아니네요... ㄷㄷㄷ...."));
        Post post21 = postRepository.save(new Post(member5, "올 해 겨울에 입을 패딩 추천 부탁드려요!", "30만원 이하대로 추천부탁드려용"));
        Post post22 = postRepository.save(new Post(member6, "정말 불만스러워요!", "ㅋ...ㅋㅋ.ㅋ..."));
        Post post23 = postRepository.save(new Post(member7, "케톤측정기 대박이네요 여러분", "강추합니다!"));
        Post post24 = postRepository.save(new Post(member1, "병원밥 진짜 노맛이네요...", "병원밥도 노맛이고, 같은 병실 사람 때문에 미치겠어요"));

//        entityManager.flush();
//        entityManager.clear();

        Comment post1_1_comment1 = commentRepository.save(new Comment(post1_1 ,member2,"안녕하세요!!"));
        Comment post1_1_comment3 = commentRepository.save(new Comment(post1_1 ,member4,"반가워요~"));
        Comment post1_1_comment4 = commentRepository.save(new Comment(post1_1 ,member5,"환영합니당"));


        Comment post1_2_comment1 = commentRepository.save(new Comment(post1_1 ,member5,"당신에게 추천해줄 메뉴는 없어"));
        Comment post1_2_comment2 = commentRepository.save(new Comment(post1_1 ,member2,"저녁은 굶으시죠"));
        Comment post1_2_comment3 = commentRepository.save(new Comment(post1_1 ,member1,"왜 그러세요.. 너무행"));


        Comment post2_comment1 = commentRepository.save(new Comment(post2, member3, "여기서 뭐 하고 있나요?"));
        Comment post2_comment2 = commentRepository.save(new Comment(post2, member4, "안녕하세요!"));
        Comment post2_comment3 = commentRepository.save(new Comment(post2, member5, "반갑습니다."));

        Comment post3_comment1 = commentRepository.save(new Comment(post3, member2, "환영합니다!"));
        Comment post3_comment2 = commentRepository.save(new Comment(post3, member4, "새로 오신 분인가요? 반가워요."));
        Comment post3_comment3 = commentRepository.save(new Comment(post3, member6, "어서 오세요!"));

        Comment post4_comment1 = commentRepository.save(new Comment(post4, member1, "ㅠㅠ 고양이 정말 귀엽죠..."));
        Comment post4_comment2 = commentRepository.save(new Comment(post4, member2, "저도 고양이가 없어서 아쉬워요."));
        Comment post4_comment3 = commentRepository.save(new Comment(post4, member5, "언젠가는 고양이를 만날 기회가 올 거예요!"));

        Comment post5_comment1 = commentRepository.save(new Comment(post5, member3, "날씨가 정말 시원해졌어요."));
        Comment post5_comment2 = commentRepository.save(new Comment(post5, member6, "벌써 가을이 온 것 같아요."));
        Comment post5_comment3 = commentRepository.save(new Comment(post5, member7, "맞아요, 이제 가을 패딩을 꺼내야 할 때가 됐어요."));

        Comment post6_comment1 = commentRepository.save(new Comment(post6, member4, "무슨 일 있나요? 괜찮으세요?"));
        Comment post6_comment2 = commentRepository.save(new Comment(post6, member5, "화 푸세요... 같이 이야기해요."));
        Comment post6_comment3 = commentRepository.save(new Comment(post6, member1, "힘내세요!"));

        Comment post7_comment1 = commentRepository.save(new Comment(post7, member2, "다이어트 응원할게요!"));
        Comment post7_comment2 = commentRepository.save(new Comment(post7, member5, "저도 같이 시작해볼까 생각 중이에요."));
        Comment post7_comment3 = commentRepository.save(new Comment(post7, member6, "간디야~ 화이팅!"));

        Comment post8_comment1 = commentRepository.save(new Comment(post8, member3, "요가나 필라테스 추천해요."));
        Comment post8_comment2 = commentRepository.save(new Comment(post8, member7, "웨이트 트레이닝도 좋은 선택이에요."));
        Comment post8_comment3 = commentRepository.save(new Comment(post8, member2, "가벼운 산책도 효과적이에요."));

        Comment post9_comment1 = commentRepository.save(new Comment(post9, member1, "무슨 일인가요? 도와드릴까요?"));
        Comment post9_comment2 = commentRepository.save(new Comment(post9, member4, "너무 무리하지 마세요."));
        Comment post9_comment3 = commentRepository.save(new Comment(post9, member5, "저도 가끔 그럴 때가 있어요. 힘내세요!"));

        Comment post10_comment1 = commentRepository.save(new Comment(post10, member3, "다이어트 식단, 어려워 보이네요."));
        Comment post10_comment2 = commentRepository.save(new Comment(post10, member6, "닭고기는 좋은 선택이에요!"));
        Comment post10_comment3 = commentRepository.save(new Comment(post10, member1, "열심히 하세요, 응원할게요."));


        Comment post11_comment1 = commentRepository.save(new Comment(post11, member1, "요즘은 M1 칩이 들어간 맥북이 좋다고 하더라구요."));
        Comment post11_comment2 = commentRepository.save(new Comment(post11, member4, "용도에 따라 다르겠지만, 게임용이라면 고성능 게이밍 노트북 추천해요."));
        Comment post11_comment3 = commentRepository.save(new Comment(post11, member5, "문서 작업용이라면 가벼운 울트라북이 괜찮아요."));

        Comment post12_comment1 = commentRepository.save(new Comment(post12, member2, "잘 가요! 앞으로도 좋은 일만 가득하길 바랍니다."));
        Comment post12_comment2 = commentRepository.save(new Comment(post12, member5, "아쉽지만, 항상 건강하고 행복하길 바라요."));
        Comment post12_comment3 = commentRepository.save(new Comment(post12, member6, "다음에 또 봐요! 언제나 응원할게요."));

        Comment post13_comment1 = commentRepository.save(new Comment(post13, member2, "축하해요! 고양이가 주인을 선택했다니 멋져요."));
        Comment post13_comment2 = commentRepository.save(new Comment(post13, member3, "이제 진정한 집사가 되었군요! 고양이와 행복한 시간 보내세요."));
        Comment post13_comment3 = commentRepository.save(new Comment(post13, member6, "환영합니다, 집사의 세계로!"));

        Comment post14_comment1 = commentRepository.save(new Comment(post14, member1, "맞아요, 가을은 정말 짧은 것 같아요."));
        Comment post14_comment2 = commentRepository.save(new Comment(post14, member4, "올해 겨울에는 눈이 많이 올까요?"));
        Comment post14_comment3 = commentRepository.save(new Comment(post14, member7, "이제 슬슬 겨울옷 준비해야겠어요."));

        Comment post15_comment1 = commentRepository.save(new Comment(post15, member1, "무슨 일이 있었나요? 진정하세요."));
        Comment post15_comment2 = commentRepository.save(new Comment(post15, member3, "화가 풀리면 좀 더 차분해질 거예요. 힘내세요."));
        Comment post15_comment3 = commentRepository.save(new Comment(post15, member7, "가끔은 화를 내는 것도 도움이 되지만, 너무 무리하지 마세요."));

        Comment post16_comment1 = commentRepository.save(new Comment(post16, member5, "케톤 측정기라니 신기하네요! 효과가 어떤가요?"));
        Comment post16_comment2 = commentRepository.save(new Comment(post16, member6, "그거 저도 한 번 써보고 싶네요!"));
        Comment post16_comment3 = commentRepository.save(new Comment(post16, member1, "다이어트 열심히 하고 계시네요. 응원합니다!"));

        Comment post17_comment1 = commentRepository.save(new Comment(post17, member2, "헉, 근육 파열이라니 괜찮으세요?"));
        Comment post17_comment2 = commentRepository.save(new Comment(post17, member3, "운동 중 부상은 항상 조심해야 해요. 빠른 회복을 기원해요."));
        Comment post17_comment3 = commentRepository.save(new Comment(post17, member4, "아프지 마세요! 쉬면서 회복하세요."));

        Comment post18_comment1 = commentRepository.save(new Comment(post18, member2, "탄수화물 줄이는 게 피부에도 좋은 영향을 주는군요."));
        Comment post18_comment2 = commentRepository.save(new Comment(post18, member5, "그렇군요! 저도 탄수화물 줄여봐야겠어요."));
        Comment post18_comment3 = commentRepository.save(new Comment(post18, member6, "얼굴 트러블이 줄었다니 대단해요! 계속 유지해보세요."));

        Comment post19_comment1 = commentRepository.save(new Comment(post19, member1, "사용해보니 어떤 점이 아쉬운가요?"));
        Comment post19_comment2 = commentRepository.save(new Comment(post19, member3, "그래도 새 노트북은 항상 기대되죠!"));
        Comment post19_comment3 = commentRepository.save(new Comment(post19, member5, "시간이 지나면 익숙해질 거예요. 잘 선택한 것 같아요."));

        Comment post20_comment1 = commentRepository.save(new Comment(post20, member2, "동물병원 진짜 비용이 만만치 않죠..."));
        Comment post20_comment2 = commentRepository.save(new Comment(post20, member6, "고양이 병원비는 정말 놀라워요. 저도 겪어봤어요."));
        Comment post20_comment3 = commentRepository.save(new Comment(post20, member7, "그래도 건강을 위해선 어쩔 수 없죠. 힘내세요!"));

        Comment post21_comment1 = commentRepository.save(new Comment(post21, member1, "패딩 추천하면, 노스페이스가 괜찮지 않을까요?"));
        Comment post21_comment2 = commentRepository.save(new Comment(post21, member3, "가격대도 적당하고, 유니클로 패딩도 좋더라고요."));
        Comment post21_comment3 = commentRepository.save(new Comment(post21, member7, "저는 쿠팡에서 할인할 때 사는 것도 추천드려요!"));

        Comment post22_comment1 = commentRepository.save(new Comment(post22, member4, "무슨 일인가요? 어떤 점이 불만이신가요?"));
        Comment post22_comment2 = commentRepository.save(new Comment(post22, member5, "ㅎㅎ 가끔씩 그런 날도 있죠."));
        Comment post22_comment3 = commentRepository.save(new Comment(post22, member1, "힘들 때는 잠시 쉬어가는 것도 좋아요."));

        Comment post23_comment1 = commentRepository.save(new Comment(post23, member3, "케톤 측정기 진짜 신기하네요! 저도 궁금해요."));
        Comment post23_comment2 = commentRepository.save(new Comment(post23, member5, "다이어트에 도움 많이 되나요?"));
        Comment post23_comment3 = commentRepository.save(new Comment(post23, member6, "오, 이거 사용 후기 기대할게요!"));

        Comment post24_comment1 = commentRepository.save(new Comment(post24, member2, "병원 음식은 진짜 맛없죠... 빨리 나으셔야 해요."));
        Comment post24_comment2 = commentRepository.save(new Comment(post24, member5, "같은 병실 사람들이 신경 쓰일 때가 많아요... 힘내세요."));
        Comment post24_comment3 = commentRepository.save(new Comment(post24, member7, "병원에서 힘드신 것 같아요. 빠른 쾌유를 기원해요!"));

    }
}
