package org.example;

import java.util.Scanner;

// 조부모 클래스인 게임캐릭터
//먼저 캐릭터가 계속 가지고 다니는 공통의 기본 속성과 메서드를 정의했습니다.
class GameCharacter {
    // protected - public과 private만 알고 있었습니다.
    // 같은 패시키 또는 자식 클래스에서 사용할 수 있도록 합니다. 퍼블릭보다 보호수준이 강하며 자식클래스에서만 생성자를 호출할 수 있습니다.
    protected String name;
    protected int age;
    protected int level;
    protected int hp;

    //생성자 , 객체가 만들어질 때 이름, 나이, 레벨, 체력을 초기화
    public GameCharacter(String name, int age, int level, int hp) {
        this.name = name;
        this.age = age;
        this.level = level;
        this.hp = hp;
    }
    // 공통으로 가지고 있는 속성을 게임 창처럼 보기위한 메서드입니다.
    public void showStatus() {
        System.out.println("\n=== 캐릭터 정보 ===");
        System.out.println("이름: " + name);
        System.out.println("나이: " + age);
        System.out.println("레벨: " + level);
        System.out.println("체력: " + hp);
    }
    // 이동하는 메서드도 추가하고 싶었지만 자바 기초 부족으로 실제 출력은 하지 못했습니다.
    public void move() {
    }
}

// 상속을 받는 중간 부모 클래스입니다. - 인간
class Human extends GameCharacter {
    protected int intelligence; //인간일 경우 지력이 추가됩니다. - 인간의 고유 속성

    // 인간 생성자 - 인간 캐릭터는 체력 변화 100으로
    public Human(String name, int age, int level) {
        super(name, age, level, 100); // 부모클래스의 생성자 호출
        this.intelligence = 30;
    }
    //메서드 move와 동일
    public void runAway() {
    }
    //부모 클래스의 상태보기를 재정의 합니다. - 기본 캐릭터 정보에 인간종족과 지력을 추가로 출력.
    @Override
    public void showStatus() {
        super.showStatus();
        System.out.println("종족: 인간");
        System.out.println("지력: " + intelligence);
    }
}

// 상속을 받는 중간 부모 클래스입니다. - 오크
class Orc extends GameCharacter {
    protected int rage; //오크만의 고유 속성

    //오크의 생성자 - 인간처럼 부모클래스를 호출하고 체력만 130으로 설정
    public Orc(String name, int age, int level) {
        super(name, age, level, 130);
        this.rage = 40; //오크의 고유 속성 - 분노
    }

    //오크의 고유 메서드
    public void smash() {
    }

    //똑같이 오크 선택 시 조부모의 상태를 재구성
    //기본 캐릭터 구조 + 오크 정보과 분노 추가
    @Override
    public void showStatus() {
        super.showStatus();
        System.out.println("종족: 오크");
        System.out.println("분노: " + rage);
    }
}

// 자식 클래스 인간전사를 인간을 상속 - 기본속성과 인간의 지력을 추가
class HumanWarrior extends Human {
    private int ap; //공격력 추가

    // 인간 전사의 생성자
    public HumanWarrior(String name, int age, int level) {
        super(name, age, level); // "인간"클래스의 생성자를 호출
        this.ap = 25; //기본 공격력 세팅
    }

    //메서드 - 베기
    public void slash() {
    }
    //메서드 - 방어하기
    public void defend() {
    }
    //인간의 상태 갱신 - 기본정보 + 인간정보 + 인간전사로 재정의
    @Override
    public void showStatus() {
        super.showStatus();
        System.out.println("직업: 전사");
        System.out.println("공격력: " + ap);
    }
}

// 자식 클래스 오크 전사 - 기본속성과 종족 오크의 분노 + 오크전사의 공격력
class OrcWarrior extends Orc {
    private int ad;

    public OrcWarrior(String name, int age, int level) {
        super(name, age, level); // 부모클래스이니 오크의 생성자 부르기
        this.ad = 35; //기본 공격력 세팅
    }

    public void slash() {
    }

    public void defend() {
    }

    //기본정보 + 오크정보 + 전사정보로 재구성
    @Override
    public void showStatus() {
        super.showStatus();
        System.out.println("직업: 전사");
        System.out.println("공격력: " + ad);
    }
}

// Main 클래스
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 캐릭터 생성 프로그램 ===");

        //사용자에게 기본정보를 입력받기.
        System.out.print("이름을 말하세요: ");
        String name = scanner.nextLine();

        System.out.print("나이를 말하세요: ");
        int age = scanner.nextInt();

        System.out.print("레벨을 말하세요: ");
        int level = scanner.nextInt();

        System.out.println("\n종족을 선택하세요.");
        System.out.println("1. 인간");
        System.out.println("2. 오크");
        System.out.print("선택: ");
        int raceChoice = scanner.nextInt();

        System.out.println("\n===직업생성===");

        // 인간을 선택한 경우
        if (raceChoice == 1) {
            HumanWarrior humanWarrior = new HumanWarrior(name, age, level);
            humanWarrior.showStatus();
        // 오크를 선택한 경우
        } else if (raceChoice == 2) {
            OrcWarrior orcWarrior = new OrcWarrior(name, age, level);
            orcWarrior.showStatus();

        } else {
            System.out.println("다시 하세요");
        }

        scanner.close();
    }
}