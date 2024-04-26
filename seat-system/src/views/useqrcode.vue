<template>
    <div id="useqrcode">
        <vue-qrcode></vue-qrcode>
    </div>
</template>



<script>
// 
import qrcode from '@/components/qrcode.vue';
export default {
// 
components: {
        'vue-qrcode': qrcode,
    },
}
</script>