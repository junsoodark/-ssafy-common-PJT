<template>
  <div>
    <div class="sample-toolbar">
		<a href="javascript:void(0)" @click="format('bold')"><span class="fa fa-bold fa-fw"></span></a>
		<a href="javascript:void(0)" @click="format('italic')"><span class="fa fa-italic fa-fw"></span></a>
		<a href="javascript:void(0)" @click="format('insertunorderedlist')"><span class="fa fa-list fa-fw"></span></a>
		<a href="javascript:void(0)" @click="setUrl()"><span class="fa fa-link fa-fw"></span></a>
		<span><input id="txtFormatUrl" placeholder="url" class="form-control"></span>
    </div>
    <p
      v-for="(value, index) in content"
      :id="sampleeditor"
      :key="index"
      contenteditable
      @input="event => onInput(event, index)"
      @keyup.delete="onRemove(index)"
      class="editor"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      content: [
        { value: 'paragraph 1' },
      ],
    };
  },
  created() {
    document.getElementById('sampleeditor').setAttribute('contenteditable', 'true');
	document.getElementById('sampleeditor2').setAttribute('contenteditable', 'true');
  },
  mounted() {
    this.updateAllContent();
  },
  methods: {
    onInput(event, index) {
      const value = event.target.innerText;
      this.content[index].value = value;
      console.log(event.target.innerHTML)
    },
    onRemove(index) {
      if (this.content.length > 1 && this.content[index].value.length === 0) {
        this.$delete(this.content, index);
        this.updateAllContent();
      }
    },
    updateAllContent() {
      this.content.forEach((c, index) => {
        const el = document.getElementById(`content-${index}`);
        el.innerText = c.value;
      });
    },
    format(command, value) {
		document.execCommand(command, false, value);
    },
    setUrl() {
		var url = document.getElementById('txtFormatUrl').value;
		var sText = document.getSelection();
		document.execCommand('insertHTML', false, '<a href="' + url + '" target="_blank">' + sText + '</a>');
		document.getElementById('txtFormatUrl').value = '';
	}
  },
};
</script>