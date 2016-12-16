/**
 * Created by Zhang on 2016/12/16.
 */
$(function(){
    $('.checkbox').on('selected',function () {
        alert($(this).val());
    });
})