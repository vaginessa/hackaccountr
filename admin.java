showMessage"                              autoupdate godmode by littleAU\n\ncredit:\n- originally created by transiate\n- cracked by unrealskill\n- leaked by B1NARY\n- support by caca22\n- autoupdate version created by littleAU\n\nfeatures:\n- doesn't need to be updated when roblox updates\n- has a good success rate\n- no need to reopen file when joining new game"

Attached = false
Timer = createTimer(nil)
MemScanCount = 0
MemScanCountMax = 10

ProcessFound = function()
	local scan = createMemScan(true)
	memscan_returnOnlyOneResult(scan, true)
	memscan_firstScan(scan, soExactValue, vtByteArray, rtTruncated, "D9 00 8B 46 20 8B 8A 2C 01 00 00 D9 1C 24 8B", nil, 0x00000000, 0xFFFFFFFF, "*W", fsmNotAligned, nil, true, false, false, false)
	memscan_waitTillDone(scan)

	local result = memscan_getOnlyResult(scan)

	if result == nil then
		MemScanCount = MemScanCount + 1
		if not (MemScanCount > MemScanCountMax) then
			sleep(100)
			ProcessFound()
		else
			showMessage"Failed to find godmode (for some reason)."
		end
	else
		result = string.format("%x", result)
		result = ("0"):rep(8-#result) .. result

		writeBytes(result, 0x90, 0x90)
	end
end

timer_setInterval(Timer, 100)
timer_onTimer(Timer, function()
	if getProcessIDFromProcessName"RobloxPlayerBeta.exe" then
		if not Attached then
			Attached = true
			openProcess(getProcessIDFromProcessName"RobloxPlayerBeta.exe")
			ProcessFound()
		end
	else
		Attached = false
	end
end)
