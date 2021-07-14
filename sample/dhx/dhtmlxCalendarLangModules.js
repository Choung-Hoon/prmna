    //Calendar localization
    dhtmlxCalendarLangModules = new Array();//create array if it doesn't exist  
    //define settings for the new language (Korean)
    dhtmlxCalendarLangModules['ko'] = {
        langname: 'ko',
        dateformat: '%Y-%m-%d',
        monthesFNames: ["1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��"],
        monthesSNames: ["1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��"],
        daysFNames: ["�Ͽ���", "������", "ȭ����", "������", "�����", "�ݿ���", "�����"],
        daysSNames: ["��", "��", "ȭ", "��", "��", "��", "��"],
        weekend: [0, 6],
        weekstart: 0,
        msgClose: "�ݱ�",
        msgMinimize: "�ּ�ȭ",
        msgToday: "����"
    }

        /*
     * Calendar ���� �� �ʱ�ȭ 
     * cal : ������ Į���� ������
     * divId : Į���� �� DIV�� ID
     * initDate : �ʱ����� (YYYY-MM-DD����)
     * relInput : ���� input�� ID
     * btnId : ���� ��ư ID
     * !!! DHTMLXCalendar,jQuery�� ���� ��� �Ǿ�� �Ѵ�.��
     */
    function l_newCalendarSet(cal, divId, initDate, inputId, btnId){

        //��ư Ŭ���� ó�� ������ Į���� �ʱ�ȭ 
        $("#"+btnId).one("click",function(){
            l_newCalendar(cal, divId, initDate, inputId, btnId);
            $(this).click();
        });
    }
    
            /*
     * Calendar ���� �� �ʱ�ȭ 
     * cal : ������ Į���� ������
     * divId : Į���� �� DIV�� ID
     * initDate : �ʱ����� (YYYY-MM-DD����)
     * relInput : ���� input�� ID
     * btnId : ���� ��ư ID     
     * !!! DHTMLXCalendar,jQuery�� ���� ��� �Ǿ�� �Ѵ�.��
     */
    function l_newCalendar(cal, divId, initDate, inputId, btnId){
        var t = new Date();
        var toYear = t.getFullYear();
        //�ʱ����� ���ų� ���Ŀ� ���� ������ ���÷�
        if(!/^(\d{4})-([0-1][0-9])-([0-3][0-9])$/.test(initDate)){
            initDate = toYear + "-" + (t.getMonth() + 1) + "-" + t.getDate();
        }
        var left = $('#'+inputId).offset().left;
        var top = $('#'+inputId).offset().top;
        $("#"+divId).css("left",left+"px"    );
        $("#"+divId).css("top" ,(top+20)+"px");

        cal = new dhtmlxCalendarObject(divId,false,{isYearEditable:true,isMonthEditable:true,yearsRange:[toYear-20,toYear]});
        cal.loadUserLanguage("ko");                                         //�ѱ� ���ö���¡
        cal.setFormatedDate("%Y-%m-%d", initDate);                          //�ʱⰪ set
        cal.setOnClickHandler(function(date){
            $("#" + inputId).val(cal.getFormatedDate("%Y-%m-%d", date));
            cal.hide();
        });                                                                 //onlick�� set
        //��ư Ŭ���� Į���� ���
        $("#"+btnId).click(function(){
            //Ŭ���� �Է��� ���ڷ� ����
            var inputDate = $("#" + inputId).val();
            if(/^(\d{4})-([0-1][0-9])-([0-3][0-9])$/.test(inputDate)){
                cal.setFormatedDate("%Y-%m-%d", inputDate);  
            }
            //Į���� ���
            l_toggleCalendar(cal);
        });
  
        //���콺 ����� �޷� �����
        $("#"+divId).bind("mouseleave",function(){cal.hide();});
        return cal;
    }

    //calendar toggle
    function l_toggleCalendar(cal){
        if(cal.isVisible()){
                cal.hide();
            } else {
                cal.show();
            }
    }