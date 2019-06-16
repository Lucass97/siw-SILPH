
var $jq = jQuery.noConflict();

$jq(document).ready(function() {
	$jq.ajax({url: "/getFotoSelezionate", success: function(result){
		$jq.each(result, function(i, val) {
			$jq("#" + val.id).addClass("active");
			$jq("#"+ val.id).attr('onclick', 'deselezionaFoto("' + val.id +'")');
			});
	}});
});

function selezionaFoto(id) {
	$jq(document).ready(function() {
		$jq.ajax({url: "/selezionaFoto/" + id, success: function(result){
			$jq("#"+ id).addClass("active");
			$jq("#"+ id).attr('onclick', 'deselezionaFoto("' + id +'")');
			console.log("selezionata foto: " + result["id"]);
		}});
	});
}

function deselezionaFoto(id) {
	$jq(document).ready(function() {
		$jq.ajax({url: "/deselezionaFoto/" + id, success: function(result){
			$jq("#"+ id).removeClass("active");
			$jq("#"+ id).attr('onclick', 'selezionaFoto("' + id +'")');
			console.log("deselezionata foto: " + result["id"]);
		}});
	});
}

