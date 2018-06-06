package com.xhwl.recruitment.util;

/**
 * @Author: guiyu
 * @Description: 对投递状态进行处理的工具类
 * @Date: Create in 下午5:57 2018/5/1
 **/
public class StatusCodeUtil {

    private final static Integer ResumeReview = 0; //简历审核

    private final static Integer HRFristReview = 1; //HR初审

    private final static Integer DepartmentWrittenExamination = 2; //部门笔试

    private final static Integer DepartmentInterview = 3; //部门面试

    private final static Integer HRInterview = 4; //HR面试

    private final static Integer Pass = 5; //已通过

    private final static Integer Refuse = 6; //已回绝


    /**
     * 数码解析，将投递状态编码解析为投递进行的步数
     * 00000 初始状态
     * 11111 通过状态
     * 2 为拒绝
     *
     * @param code
     * @return
     */
    public static Integer codeAnalysis(String code) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                return i;
            }
            if (code.charAt(i) == '2') {
                return Refuse;
            }
        }
        //全为1才能到此处
        return Pass;
    }

    /**
     * 投递状态编码流转
     *
     * @param code   投递状态的编码
     * @param choice 你的选择，通过或是拒绝,通过为1，拒绝为2
     * @return 流转后的编码
     */
    public static String codeChange(String code, Integer choice) {
        if (codeAnalysis(code) == Pass || codeAnalysis(code) == Refuse) {
            return code;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                stringBuilder.append(code.charAt(i));
            }
            if (code.charAt(i) == '0') {
                if (choice == 1) {
                    stringBuilder.append('1');
                } else {
                    stringBuilder.append('2');
                }
                for (int j = i + 1; j < code.length(); j++) {
                    stringBuilder.append(code.charAt(j));
                }
                break;
            }
        }
        return String.valueOf(stringBuilder);
    }

    /**
     * 投递状态编码,用于投递时初始
     *
     * @return
     */
    public static String initCode() {
        return "00000";
    }

    /**
     * 投递状态编码转化为前端需要的样式
     * <p>
     * 返回一个整数 1~4表示审核中、部门笔试中、部门面试中、HR面试中
     * -1~-4表示审核被拒、部门面试被拒、部门面试被拒、HR面试被拒
     * 5表示全部步骤通过
     *
     * @param code
     * @return
     */
    public static String code2View(String code) {
        Integer status = codeAnalysis(code);
        if (status == ResumeReview || status == HRFristReview) {
            return "1";
        } else if (status == DepartmentWrittenExamination) {
            return "2";
        } else if (status == DepartmentInterview) {
            return "3";
        } else if (status == HRInterview) {
            return "4";
        } else if (status == Pass) {
            return "5";
        } else {
            //拒绝的情况
            Integer refuseStep = 0;
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '2') {
                    refuseStep = i;
                    break;
                }
            }
            if (refuseStep == ResumeReview || refuseStep == HRFristReview) {
                return "-1";
            } else if (refuseStep == DepartmentWrittenExamination) {
                return "-2";
            } else if (refuseStep == DepartmentInterview) {
                return "-3";
            } else if (refuseStep == HRInterview) {
                return "-4";
            }
        }
        return "1";
    }

    /**
     * 获取回绝时状态
     *
     * @param code
     * @return
     */
    public static String getRefuseStep(String code) {
        Integer status = codeAnalysis(code);
        if (status == Refuse) {
            Integer refuseStep = 0;
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '2') {
                    refuseStep = i;
                    break;
                }
            }

            if (refuseStep == ResumeReview) {
                return "简历审核";
            } else if (refuseStep == HRFristReview) {
                return "HR初审";
            } else if (refuseStep == DepartmentWrittenExamination) {
                return "部门笔试";
            } else if (refuseStep == DepartmentInterview) {
                return "部门面试";
            } else if (refuseStep == HRInterview) {
                return "HR面试";
            } else {
                return "其他步骤";
            }
        } else {
            return "未被拒绝";
        }
    }

    /**
     * 从拒绝池中捞取，回退到拒绝前状态
     *
     * @param code
     * @return
     */
    public static String cancelRefuse(String code) {
        Integer status = codeAnalysis(code);
        StringBuilder newCode = new StringBuilder(code);
        if (status == Refuse) {
            for (int i = 0; i < newCode.length(); i++) {
                if (code.charAt(i) == '2') {
                    newCode.setCharAt(i, '0');
                    break;
                }
            }
            return String.valueOf(newCode);
        } else {
            return code;
        }
    }

    /**
     * 传入简历审核的步数，传出状态编码，其中拒绝的编码为 2 只能用于模糊查询
     *
     * @param step
     * @return
     */
    public static String getCode(Integer step) {
        if (step == ResumeReview) {
            return "00000";
        } else if (step == HRFristReview) {
            return "10000";
        } else if (step == DepartmentWrittenExamination) {
            return "11000";
        } else if (step == DepartmentInterview) {
            return "11100";
        } else if (step == HRInterview) {
            return "11110";
        } else if (step == Pass) {
            return "11111";
        } else {
            return "2";
        }
    }
}
