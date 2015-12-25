import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MemberDatabase {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<Member>  memberList  = new ArrayList<>();
    private ArrayList<Integer> blankIdList = new ArrayList<>();
    private static int nextId = 1;

    // 登録
    private void register() {
        try {
            System.out.println("--- member registration ---");

            System.out.print("input name: ");
            String name = br.readLine();

            System.out.print("input address: ");
            String address = br.readLine();

            System.out.print("input age: ");
            int age = Integer.parseInt(br.readLine());

            int id = blankIdList.isEmpty() ? nextId++ : blankIdList.remove(0);
            Member m = new Member(id, name, address, age);
            memberList.add(m);
            System.out.println("registered new member : " + m.nameAndID());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("input number please");
        }
    }

    // 退会
    private void withdraw() {
        withdraw(idInputConsole());
    }

    private void withdraw(int id) {
        System.out.println("--- member withdrawal ---");
        Member m = searchMember(id);
        if(m != null) {
            memberList.remove(m);
            blankIdList.add(id);
            Collections.sort(blankIdList);
            System.out.println("member : " + m.nameAndID() + " withdrew");
        }
    }

    // メンバー情報の表示
    private void memberInformation() {
        memberInformation(idInputConsole());
    }

    private void memberInformation(int id) {
        System.out.println("--- show member's information ---");
        Member m = searchMember(id);
        if(m != null)
            System.out.println(m.infoDetail());
    }

    // メンバーの一覧を表示
    private void showList(){
        System.out.println("--- member list ---");
        if(!memberList.isEmpty()) {
            System.out.println("ID, Name, Address, Age, Mile, Status");
            for (Member m : memberList) {
                System.out.println(m.infoLine());
            }
        }
        else
            System.out.println("There is no member.");
    }

    // 会員の追跡
    private void traceMember() {
        traceMember(idInputConsole());
    }

    private void traceMember(int id) {
        System.out.println("--- trace member ---");
        Member m = searchMember(id);
        if(m != null)
            trace(m);
    }

    // 追跡処理本体のヘルパーメソッド
    private void trace(Member m) {
        System.out.println("--- member tracing mode ---");
        System.out.println("<help> to get usage");
        while (true) {
            String command;
            int num = 0;

            System.out.print(m.nameAndID() + "> ");

            while (true) try {
                String line = br.readLine();
                Scanner s = new Scanner(line);
                command = s.next().trim();
                if (s.hasNext()) {
                    num = s.nextInt();
                }
                break;
            } catch (NoSuchElementException e) {
                System.err.println("invalid command. <help> to get usage");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (command == null) {
                System.out.println("--- quit tracing mode ---");
                return;
            } else {
                switch (command) {
                    case "flight":
                    case "f":
                        m.flight(num);
                        break;
                    case "shop":
                    case "s":
                        m.shopping(num);
                        break;
                    case "usemile":
                    case "u":
                        m.useMile(num);
                        break;
                    case "quit":
                    case "q":
                        System.out.println("--- quit tracing mode ---");
                        return;
                    case "help":
                        traceHelp();
                        break;
                    default:
                        System.err.println("unknown command. Try again");
                }
            }
        }
    }

    // IDを入力させる
    private int idInputConsole() {
        while (true) {
            try {
                System.out.print("input member ID: ");
                int ID = Integer.parseInt(br.readLine());
                return ID;
            } catch (NumberFormatException e) {
                System.err.println("input a number! Try again.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 検索用ヘルパーメソッド
    private Member searchMember(int id) {
        Member member = null;
        for (Member m : memberList) {
            if (m.getID() == id) {
                member = m;
            }
        }
        if (member != null) {
            return member;
        } else {
            System.out.println("member ID " + id + " is not exist. Try again");
        }
        return null;
    }


    // コマンドヘルプ
    private void help() {
        System.out.println("register / r: register new member");
        System.out.println("withdraw / w: withdraw member");
        System.out.println("info     / i: show member's information");
        System.out.println("trace    / t: trace member");
        System.out.println("shortcut -> w / i / t <member ID>");
        System.out.println("list     / l: show list of members");
        System.out.println("quit     / q: quit database system");
    }

    // 追跡モードのコマンドヘルプ
    private void traceHelp() {
        System.out.println("flight  / f <distance>: flight and earn mile. member can get bonus mile depending on member status");
        System.out.println("shop    / s <price>   : shop and earn mile. member can receive discount depending on member status");
        System.out.println("usemile / u <mile>    : use mile for something.");
        System.out.println("quit    / q           : quit tracing mode");
    }

    // 終了
    private void quit() {
        System.out.println("bye");
        System.exit(0);
    }

    public void console() {
        System.out.println("--- mile member database system ---");
        System.out.println("<help> to get usage");
        while (true) {
            String command;
            int id = 0;
            while (true) {
                try {
                    System.out.print("> ");
                    String line = br.readLine();
                    Scanner s = new Scanner(line);
                    command = s.next().trim();
                    if (s.hasNext()) {
                        int i = s.nextInt();
                        if (i > 0) {
                            id = i;
                            break;
                        } else {
                            /* do nothing */
                        }
                    }
                    break;
                } catch (NoSuchElementException e) {
                    System.err.println("invalid command. <help> to get usage");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (command == null) {
                quit();
            } else {
                switch (command) {
                    case "register":
                    case "r":
                        register();
                        break;
                    case "withdraw":
                    case "w":
                        if (id != 0)
                            withdraw(id);
                        else
                            withdraw();
                        break;
                    case "info":
                    case "i":
                        if (id != 0)
                            memberInformation(id);
                        else
                            memberInformation();
                        break;
                    case "trace":
                    case "t":
                        if (id != 0)
                            traceMember(id);
                        else
                            traceMember();
                        break;
                    case "list":
                    case "l":
                        showList();
                        break;
                    case "quit":
                    case "q":
                        quit();
                        break;
                    case "help":
                        help();
                        break;
                    default:
                        System.err.println("unknown command. Try again");
                        break;
                }
            }
        }
    }
}
