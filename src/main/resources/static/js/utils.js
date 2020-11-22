
export const ajaxConnect = (url, type, data) => {
	return $.ajax({
	        url: url,
	        type: type,
	        data: data
	    })
}

